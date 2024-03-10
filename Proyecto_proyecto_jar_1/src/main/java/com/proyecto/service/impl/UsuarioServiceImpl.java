/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

import com.proyecto.dao.UsuarioDao;
import com.proyecto.domain.Usuario;
import com.proyecto.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fallaa8
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
    
 @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        var lista = usuarioDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)

    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public Usuario getUsuarioPorEmailYPassword(String email, String password) {
        return usuarioDao.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean existeUsuarioPorEmail(String email) {
       return usuarioDao.existsByEmail(email);
    }

    @Override
    public Usuario getUsuarioporEmail(String email) {
        return usuarioDao.findByEmail(email);
    }
 
}
