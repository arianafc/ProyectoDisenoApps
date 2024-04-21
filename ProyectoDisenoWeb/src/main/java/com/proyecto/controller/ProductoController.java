/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

/**
 *
 * @author Jorge
 */
import com.proyecto.dao.CategoriaDao;
import com.proyecto.dao.TallasDao;
import com.proyecto.domain.Categoria;
import com.proyecto.domain.Comentarios;
import com.proyecto.domain.Estilo;
import com.proyecto.domain.Producto;
import com.proyecto.domain.Tallas;
import com.proyecto.domain.Usuario;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.ColorService;
import com.proyecto.service.ComentariosService;
import com.proyecto.service.EstiloService;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.ProductoService;
import com.proyecto.service.TallasService;
import com.proyecto.service.UsuarioService;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    private CategoriaDao categoriaDao;
    @Autowired
    private EstiloService estiloService;
    @Autowired
    private TallasService tallasService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ComentariosService comentariosService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos();
        var categorias = categoriaService.getCategorias();
        var estilos = estiloService.getEstilos();
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        model.addAttribute("estilos", estilos);
        return "producto/listado";
    }

    @GetMapping("/vistaProducto")
    public String producto(Model model) {
        var estilos = estiloService.getEstilos();
        model.addAttribute("estilos", estilos);
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "/producto/vistaProducto";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "producto/modificar";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            producto.setIdProducto(0L);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "producto",
                            producto.getIdProducto())
            );
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(@PathVariable("idProducto") Long idProducto) {
        productoService.deleteProducto(idProducto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(@PathVariable("idProducto") Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("estilos", estiloService.getEstilos());
        return "producto/modificar";
    }

    @GetMapping("/vistaProductoDetalle/{idProducto}")
    public String vistaProductoDetalle(@PathVariable("idProducto") Producto producto, Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        producto = productoService.getProducto(producto);
        var productos = productoService.getProductos();
        var categorias = categoriaService.getCategorias();
        var estilos = estiloService.getEstilos();
        var tallas = tallasService.findByidProducto(producto.getIdProducto());
        var colores = colorService.findByidProducto(producto.getIdProducto());
        var comentarios = comentariosService.findByIdProducto(producto.getIdProducto());
        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        model.addAttribute("estilos", estilos);
        model.addAttribute("producto", producto);
        model.addAttribute("tallas", tallas);
        model.addAttribute("colores", colores);
        model.addAttribute("comentarios", comentarios);
        return "producto/vistaProductoDetalle";
    }

    @GetMapping("busquedaProducto")
    public String busquedaProducto(Model model) {
        return "/producto/busquedaProducto";
    }

    @GetMapping("guiaTallas")
    public String guiaTallas() {
        return "/producto/guiaTallas";
    }

    @GetMapping("/Carrito")
    private String Carrito() {
        return "/producto/Carrito";
    }

    @GetMapping("/agregarProducto")
    private String agregarProducto(Model model, Producto producto) {
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("estilos", estiloService.getEstilos());
        return "/producto/agregarProducto";
    }

    @GetMapping("/vistaProducto/{nombre}")
    public String getProductsByCategoryName(@PathVariable("nombre") String nombreCategoria, Model model) {
        Categoria categoria = categoriaService.getCategoriaByName(nombreCategoria);
        List<Producto> productos = productoService.findByCategoria(categoria);
        model.addAttribute("nombreCategoria", nombreCategoria);
        model.addAttribute("productos", productos);
        model.addAttribute("estilos", estiloService.getEstilos());

        return "/producto/vistaProducto";
    }

    @GetMapping("/vistaProducto/{nombre}/{estilo}")
    public String getProductsByEstiloName(@PathVariable("nombre") String nombreCategoria, @PathVariable("estilo") String nombreEstilo, Model model) {
        Categoria categoria = categoriaService.getCategoriaByName(nombreCategoria);
        Estilo estilo = estiloService.getEstiloByName(nombreEstilo);
        List<Producto> productos = productoService.findByCategoriaAndEstilo(categoria, estilo);
        model.addAttribute("nombreCategoria", nombreCategoria);
        model.addAttribute("nombreEstilo", nombreEstilo);
        model.addAttribute("productos", productos);
        model.addAttribute("estilos", estiloService.getEstilos());

        return "/producto/vistaProducto";
    }

    @GetMapping("/vistaProducto/{nombre}/marca/{marca}")
    public String getProductosMarca(@PathVariable("nombre") String nombreCategoria, @PathVariable("marca") String marca, Model model) {
        Categoria categoria = categoriaService.getCategoriaByName(nombreCategoria);
        List<Producto> productos = productoService.filtrarMarcaYCategoria(categoria.getIdCategoria(), marca);
        model.addAttribute("nombreCategoria", nombreCategoria);
        model.addAttribute("productos", productos);
        model.addAttribute("estilos", estiloService.getEstilos());
        return "/producto/vistaProducto";
    }

    @GetMapping("/vistaProducto/marca/{marca}")
    public String getAllMarca(@PathVariable("marca") String marca, Model model) {
        List<Producto> productos = productoService.findByMarca(marca);
        model.addAttribute("productos", productos);
        model.addAttribute("estilos", estiloService.getEstilos());
        return "/producto/vistaProducto";
    }

    @GetMapping("/query1")
    public String consultaQuery1(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, @RequestParam(value = "nombreEstilo") String nombreEstilo,
            @RequestParam(value = "marca") String marca, Model model) {
        Estilo estilo = estiloService.getEstiloByName(nombreEstilo);
        var productos = productoService.findByFiltros(precioInf, precioSup, estilo.getIdEstilo(), marca);
        model.addAttribute("productos", productos);
        model.addAttribute("estilos", estiloService.getEstilos());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        model.addAttribute("nombreEstilo", nombreEstilo);
        model.addAttribute("marca", marca);
        return "/producto/vistaProducto";
    }

    @PostMapping("/guardarComentario")
    public String comentarioGuardar(Comentarios comentario,
            @RequestParam("talla") double talla, @RequestParam("Color") String color,
            @RequestParam("idProducto") Long idProducto, @RequestParam("comentario") String comentarios, Principal principal) {
        Producto producto = productoService.findByIdProducto(idProducto);
        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        comentario.setColor(color);
        comentario.setIdProducto(idProducto);
        comentario.setIdUsuario(usuario.getIdUsuario());
        comentario.setTalla(talla);
        comentario.setComentario(comentarios);
        comentario.setFecha(Calendar.getInstance().getTime());
        comentario.setUsuario(usuario.getNombre()+" "+usuario.getApellidos());
        comentario.setNombreProducto(producto.getNombre());
        comentariosService.save(comentario);
        return "redirect:/producto/vistaProducto";
    }

 

}
