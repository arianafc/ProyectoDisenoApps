/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

/**
 *
 * @author jorge
 */
import com.proyecto.service.ListaDeseosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listaDeseos")
public class ListaDeseosController {

    @Autowired
    private ListaDeseosService listaDeseosService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var listaDeseos = listaDeseosService.obtenerTodasLasListasDeseos();
        model.addAttribute("listaDeseos", listaDeseos);
        return "listaDeseos/listado"; // Asegúrate de que este es el nombre correcto de tu plantilla
    }

    // Temporalmente removemos el uso de idUsuario para simplificar
    @GetMapping("/agregar/{idProducto}")
    public String agregarProductoDeseado(@PathVariable Long idProducto) {
        // Implementación temporal que ignora el usuario
        // Deberás modificar esta lógica para asociar correctamente el producto con un usuario
        listaDeseosService.agregarProductoDeseado(null, idProducto); // Ajustar según la implementación real
        return "redirect:/listaDeseos/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminarProductoDeseado(@PathVariable Long idProducto) {
        // Implementación temporal que ignora el usuario
        // Deberás modificar esta lógica para asociar correctamente el producto con un usuario
        listaDeseosService.eliminarProductoDeseado(null, idProducto); // Ajustar según la implementación real
        return "redirect:/listaDeseos/listado";
    }
}
