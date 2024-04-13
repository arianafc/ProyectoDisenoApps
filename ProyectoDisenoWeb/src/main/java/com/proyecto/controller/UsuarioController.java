/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ariana
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/inicio")
    private String inicio() {
        return "/usuario/inicio";
    }

    @GetMapping("/adminInicio")
    private String adminInicio() {
        return "/usuario/adminInicio";
    }

    @GetMapping("/vendedorInicio")
    private String vendedorInicio() {
        return "/usuario/vendedorInicio";
    }

    @GetMapping("/crearCuenta")
    private String crearCuenta() {
        return "/usuario/crearCuenta";
    }

    @GetMapping("/asignarRol")
    private String asignarRol() {
        return "/usuario/asignarRol";
    }

    @GetMapping("/miCuenta")
    private String miCuenta() {
        return "/usuario/miCuenta";
    }
}
