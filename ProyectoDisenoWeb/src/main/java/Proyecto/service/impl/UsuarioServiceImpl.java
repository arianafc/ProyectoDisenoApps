/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto.service.impl;

import Proyecto.dao.UsuarioDao;
import Proyecto.domain.Usuario;
import Proyecto.service.UsuarioService;
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

    @Transactional(readOnly = true)
    @Override
    public Usuario getUsuario(String email) {
        return usuarioDao.findByEmail(email);
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

   


    
    

    
    
    
}
