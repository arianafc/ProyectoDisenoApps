/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

/**
 *
 * @author Ariana
 */
import com.proyecto.dao.UsuarioDao;
import com.proyecto.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.service.UsuarioService;
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
 

    @GetMapping("/login")
    private String login(Model model) {
        return "/usuario/login";
    }

    @PostMapping("/inicio")
    private String inicio(Model model, @RequestParam(value = "email") String email, @RequestParam(value = "password") String pass) {
        Usuario usuario = usuarioService.getUsuarioporEmail(email);
        if(usuario==null){
            model.addAttribute("error", "Correo o usuario incorrecto");
             return "/usuario/login";
        }
        if (!email.equals(usuarioService.getUsuarioporEmail(email).getEmail()) || !pass.equals(usuarioService.getUsuarioporEmail(email).getPassword())) {
            model.addAttribute("error", "Correo o usuario incorrecto");
            return "/usuario/login";
        } else {
            return "/usuario/inicio";
        }

    }

    
    @GetMapping("/crearCuenta")
    private String crearCuenta() {
        return "/usuario/crearCuenta";
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario){
        usuario.setDireccion("");
        usuario.setRol("3");
        usuarioService.save(usuario);
        return "redirect:/usuario/login";
    }
    

    }


