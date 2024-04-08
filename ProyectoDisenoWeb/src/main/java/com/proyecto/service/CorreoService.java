/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import jakarta.mail.MessagingException;

/**
 *
 * @author Ariana
 */
public interface CorreoService {
     public void enviarCorreoHtml(
           String para,
           String asunto,
           String contenidoHTML)
           throws MessagingException; 
}
