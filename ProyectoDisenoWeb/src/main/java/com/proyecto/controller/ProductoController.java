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
import com.proyecto.domain.Categoria;
import com.proyecto.domain.Estilo;
import com.proyecto.domain.Producto;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.EstiloService;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.ProductoService;
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

    @GetMapping("busquedaProducto")
    public String busquedaProducto(Model model) {
        return "/producto/busquedaProducto";
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
//        Long idCategoria = categoria.getIdCategoria();
        List<Producto> productos = productoService.findByCategoriaAndEstilo(categoria, estilo);
        model.addAttribute("nombreCategoria", nombreCategoria);
        model.addAttribute("nombreEstilo", nombreEstilo);
        model.addAttribute("productos", productos);
        model.addAttribute("estilos", estiloService.getEstilos());

        return "/producto/vistaProducto";
    }

//        @GetMapping("/vistaProducto/{nombre}/{marca}")
//    public String getProductsByMarca(@PathVariable("nombre") String nombreCategoria, @PathVariable("marca") String marca, Model model) {   
//        Categoria categoria = categoriaService.getCategoriaByName(nombreCategoria);
// 
//        List<Producto> productos = productoService.findByCategoriaAndMarca(categoria, marca); 
//        model.addAttribute("nombreCategoria", nombreCategoria);
//        model.addAttribute("marca", marca);
//        model.addAttribute("productos", productos);
//        model.addAttribute("estilos", estiloService.getEstilos());
//        
//        return "/producto/vistaProducto";
//    }
    @GetMapping("/producto/vistaProducto/{nombreCategoria}/{marca}")
    public String vistaProductosByCategoriaAndMarca(
        @PathVariable("nombreCategoria") String nombreCategoria,
        @PathVariable("marca") String marca,
        Model model
    ) {
        Categoria categoria = categoriaService.getCategoriaByName(nombreCategoria);
        List<Producto> productos = productoService.findByCategoriaAndMarca(categoria, marca);
        model.addAttribute("productos", productos);
        return "producto/vistaProducto";
    }
}
