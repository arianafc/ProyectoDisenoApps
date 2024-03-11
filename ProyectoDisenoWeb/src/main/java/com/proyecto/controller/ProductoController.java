/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;
/**
 *
 * @author Jorge
 */
import com.proyecto.domain.Producto;
import com.proyecto.service.ProductoService;
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

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        return "producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/agregarProducto";
    }

    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        productoService.saveProducto(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(@PathVariable("idProducto") Long idProducto) {
        productoService.deleteProducto(idProducto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(@PathVariable("idProducto") Long idProducto, Model model) {
        Producto producto = productoService.getProducto(idProducto);
        model.addAttribute("producto", producto);
        return "producto/modificar";
    }
    

    @GetMapping("busquedaProducto")
    public String busquedaProducto(Model model){
        return "/producto/busquedaProducto";
    }

}
