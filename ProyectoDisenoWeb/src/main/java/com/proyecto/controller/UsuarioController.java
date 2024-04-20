/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Rol;
import com.proyecto.domain.Usuario;
import com.proyecto.service.RolService;
import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ariana
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;

    @GetMapping("/inicio")
    private String inicio() {
        return "/usuario/inicio";
    }

    @GetMapping("/adminInicio")
    private String adminInicio(Model model, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        model.addAttribute("usuario", usuario);
        return "/usuario/adminInicio";
    }

    @GetMapping("/vendedorInicio")
    private String vendedorInicio() {
        return "/usuario/vendedorInicio";
    }

    @GetMapping("/miCuenta")
    private String miCuenta() {
        return "/usuario/miCuenta";
    }

    @GetMapping("/asignarRol")
    public String listado(Model model) {
        var roles = rolService.getRols();
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        return "usuario/asignarRol";
    }

//
//    @GetMapping("/nuevo")
//    public String productoNuevo(Producto producto) {
//        return "producto/modificar";
//    }
//
//    @Autowired
//    private FirebaseStorageService firebaseStorageService;
//
    @PostMapping("/guardar")
    public String productoGuardar(Rol rol) {
        rolService.saveRol(rol);
        return "redirect:/usuario/asignarRol";
    }
//

    @GetMapping("/eliminar/{idRol}")
    public String productoEliminar(@PathVariable("idRol") Long idRol) {
        rolService.delete(idRol);
        return "usuario/asignarRol";
    }
//

    @GetMapping("/modificar/{idRol}")
    public String productoModificar(@PathVariable("idRol") Rol rol, Model model) {
        rol = rolService.getRol(rol);

        var roles = rolService.getRols();
        model.addAttribute("rol", rol);
        model.addAttribute("roles", roles);

        return "usuario/modificar";
    }
}
