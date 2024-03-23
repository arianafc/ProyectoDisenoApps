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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/listaDeseos")
public class ListaDeseosController {

    @Autowired
    private ListaDeseosService listaDeseosService;

    @GetMapping("/listado")
    public String listarDeseos(Model model, Long idUsuario) {
        model.addAttribute("listaDeseos", listaDeseosService.obtenerListaDeseosList(idUsuario));
        return "listaDeseos/listado";
    }

    @GetMapping("/agregar/{idProducto}")
    public String agregarProductoDeseado(@PathVariable Long idProducto, Long idUsuario) {
        listaDeseosService.agregarProductoDeseado(idUsuario, idProducto);
        return "redirect:/listaDeseos/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminarProductoDeseado(@PathVariable Long idProducto, Long idUsuario) {
        listaDeseosService.eliminarProductoDeseado(idUsuario, idProducto);
        return "redirect:/listaDeseos/listado";
    }
}