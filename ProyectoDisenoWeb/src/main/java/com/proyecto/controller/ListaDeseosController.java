/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

/**
 *
 * @author jorge
 */
import com.proyecto.domain.ListaDeseos;
import com.proyecto.domain.Producto;
import com.proyecto.domain.Usuario;
import com.proyecto.service.ListaDeseosService;
import com.proyecto.service.ProductoService;
import com.proyecto.service.UsuarioService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/listaDeseos")
public class ListaDeseosController {

    @Autowired
    private ListaDeseosService listaDeseosService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        var listasDeseos = listaDeseosService.findByIdUsuario(usuario.getIdUsuario());
        model.addAttribute("deseos", listasDeseos);
        return "listaDeseos/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("idProducto") Long idProducto, Principal principal) {

        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);

        Producto producto = productoService.findByIdProducto(idProducto);
        ListaDeseos nuevoItem = new ListaDeseos(producto, usuario);
        listaDeseosService.agregarProductoDeseado(nuevoItem);
        return "redirect:/producto/vistaProducto";
    }
}
