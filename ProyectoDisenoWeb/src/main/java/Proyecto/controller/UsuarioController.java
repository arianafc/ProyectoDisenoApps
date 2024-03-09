/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto.controller;

/**
 *
 * @author Ariana
 */
import Proyecto.dao.UsuarioDao;
import Proyecto.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import Proyecto.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;

import Proyecto.service.UsuarioService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private UsuarioDao usuarioDao;

    @GetMapping("/inicioSesion")
    private String inicioSesion(Model model) {
        return "/usuario/inicioSesion";
    }

    @PostMapping("/inicioSesion")
    private String inicioSesion(Model model, @RequestParam(value = "email") String email, @RequestParam(value = "password") String pass) {
        Usuario usuario = usuarioService.getUsuario(email);
        if (!email.equals(usuario.getEmail()) && !pass.equals(usuario.getPassword())) {
            model.addAttribute("error", "Correo o usuario incorrecto");
            return "index";
        } else {
            return "/usuario/inicioSesion";
        }

    }

    @GetMapping("/index")
    private String mostrarInicio() {
        return "index";
    }

    @GetMapping("/crearCuenta")
    private String crearCuenta() {
        return "/usuario/crearCuenta";
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario){
        usuario.setDireccion(null);
        usuario.setRol("3");
        usuarioService.save(usuario);
        return "redirect:/usuario/inicioSesion";
    }
    

    }


