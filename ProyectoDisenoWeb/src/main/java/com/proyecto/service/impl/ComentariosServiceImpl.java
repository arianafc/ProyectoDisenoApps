/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

import com.proyecto.dao.ComentariosDao;
import com.proyecto.domain.Comentarios;
import com.proyecto.service.ComentariosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentariosServiceImpl implements ComentariosService{
    @Autowired
    private ComentariosDao comentariosDao;
    @Override
    public List<Comentarios> getComentarios() {
       var lista = comentariosDao.findAll();
       return lista;
    }

    @Override
    public Comentarios getComentario(Comentarios comentario) {
        return comentariosDao.findById(comentario.getIdComentario()).orElse(null);
    }

    @Override
    public void save(Comentarios comentario) {
        comentariosDao.save(comentario);
    }

    @Override
    public void delete(Long idComentario) {
        comentariosDao.deleteById(idComentario);
    }

    public List<Comentarios> findByIdProducto(Long idProducto) {
        return comentariosDao.findByIdProducto(idProducto);
    }
    
    
}
