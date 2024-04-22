/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

/**
 *
 * @author jorge
 */
import com.proyecto.dao.ListaDeseosDao;
import com.proyecto.domain.ListaDeseos;
import com.proyecto.service.ListaDeseosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ListaDeseosServiceImpl implements ListaDeseosService {

    @Autowired
    private ListaDeseosDao listaDeseosDao;

    @Override
    @Transactional(readOnly = true)
    public List<ListaDeseos> obtenerTodasLasListasDeseos() {
        return listaDeseosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListaDeseos> findByIdUsuario(Long idUsuario) {
        return listaDeseosDao.findByIdUsuario(idUsuario);
    }
    
    @Override
    @Transactional
    public void agregarProductoDeseado(ListaDeseos listaDeseos) {
       listaDeseosDao.save(listaDeseos);
    }

    @Override
    @Transactional
    public void eliminarProductoDeseado(Long id) {
       listaDeseosDao.deleteById(id);
    }


}
