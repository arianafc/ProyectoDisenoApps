/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

import com.proyecto.dao.RolDao;
import com.proyecto.domain.Rol;
import com.proyecto.service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService{
    @Autowired
    private RolDao rolDao;
    @Override
    public List<Rol> getRols() {
       var lista = rolDao.findAll();
       return lista;
    }

    @Override
    public Rol getRol(Rol rol) {
        return rolDao.findById(rol.getIdRol()).orElse(null);
    }

    @Override
    public void saveRol(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    public void delete(Long idRol) {
        rolDao.deleteById(idRol);
    }
    
}
