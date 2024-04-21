/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Comentarios;
import java.util.List;

/**
 *
 * @author Ariana
 */
public interface ComentariosService {
    public List<Comentarios> getComentarios();
    public Comentarios getComentario(Comentarios comentario);   
     
    public void save(Comentarios comentario);          
  
    public void delete(Long comentario);
    
    public List<Comentarios> findByIdProducto(Long idProducto);
}
