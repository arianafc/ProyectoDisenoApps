/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Usuario;
import com.proyecto.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ariana
 */
public interface RegistroService {
    public Model activar(Model model, String usuario, String clave);//activar por el correo que se envia
    public Model crear(Model model, Usuario usuario) throws MessagingException;
    public void activar(Usuario usuario, MultipartFile imagenFile);//activa a nivel de codigo de la BD
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException;
}
