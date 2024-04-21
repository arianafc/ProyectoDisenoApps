package com.proyecto.controller;

import com.proyecto.domain.Factura;
import com.proyecto.domain.Producto;
import com.proyecto.domain.Item;
import com.proyecto.domain.Usuario;
import com.proyecto.domain.Venta;
import com.proyecto.service.ColorService;
import com.proyecto.service.FacturaService;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.UsuarioService;
import com.proyecto.service.ItemService;
import static com.proyecto.service.ItemService.listaItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.proyecto.service.ProductoService;
import com.proyecto.service.TallasService;
import com.proyecto.service.VentaService;
import java.security.Principal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private TallasService tallasService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private VentaService ventaService;

    @GetMapping("/")
    private String listado(Model model) {
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "/index";
    }

    //Para ver el carrito
    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal",
                carritoTotalVenta);
        return "/carrito/listado";
    }

    //Para Agregar un producto al carrito
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregarItem(Model model, Item item, @RequestParam("talla") double talla, @RequestParam("color") String color) {
        Item item2 = itemService.get(item);
        if (item2 == null) {
            Producto producto = productoService.getProducto(item);
            item2 = new Item(producto, talla, color);
        }

        itemService.save(item2);
        var lista = itemService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        model.addAttribute("tallas", tallasService.findByidProducto(item2.getIdProducto()));
        model.addAttribute("colores", tallasService.findByidProducto(item2.getIdProducto()));
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    //Para mofificar un producto del carrito
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificarItem(Item item, Model model) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }

    //Para eliminar un elemento del carrito
    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    //Para actualizar un producto del carrito (cantidad)
    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {
        itemService.actualiza(item);
        return "redirect:/carrito/listado";
    }
    @Autowired
    FirebaseStorageService firebaseStorageService;
    //Para facturar los productos del carrito... no implementado...
    @PostMapping("/facturar/carrito")
    public String facturarCarrito(@RequestParam("direccion") String direccion, @RequestParam("metodoPago") String metodoPago,  
            @RequestParam("imagenFile") MultipartFile imagenFile) {
       
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return "";
        }

        Usuario usuario = usuarioService.getUsuarioPorUsername(username);

        if (usuario == null) {
            return "";
        }

        Factura factura = new Factura(usuario.getIdUsuario(), direccion, metodoPago);
        facturaService.save(factura);
        
        if (!imagenFile.isEmpty()) {
                // Actualizar la ruta de la imagen y guardar la imagen
                factura.setRutaImagen(firebaseStorageService.cargaImagen(
                        imagenFile, "facturas", factura.getIdFactura()));
            }
        
        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Producto: " + i.getDescripcion()
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());
            Venta venta = new Venta(factura.getIdFactura(), i.getIdProducto(), i.getPrecio(), i.getCantidad());
            ventaService.saveVenta(venta);
            Producto producto = productoService.findByIdProducto(i.getIdProducto());
            producto.setExistencias(producto.getExistencias()-i.getCantidad());
            productoService.save(producto);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaService.save(factura);
        listaItems.clear();
        return "redirect:/carrito/orden";
    }
    
    @GetMapping("/carrito/checkout")
    public String checkout(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        var items = itemService.gets();
        model.addAttribute("usuario", usuario);
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal",
                carritoTotalVenta);
        return "/carrito/checkout";
    }
    
    @GetMapping("/carrito/orden")
    public String orden(Model model, Principal principal) {
        return "/carrito/orden";
    }
}
