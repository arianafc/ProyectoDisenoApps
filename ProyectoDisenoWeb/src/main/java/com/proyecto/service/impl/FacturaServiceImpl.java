/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

import com.proyecto.dao.FacturaDao;
import com.proyecto.domain.Factura;
import com.proyecto.service.FacturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ariana
 */
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaDao facturaDao;
    
    @Override
    public List<Factura> getRols() {
       return facturaDao.findAll();
    }

    @Override
    public Factura getFactura(Factura factura) {
        return facturaDao.findById(factura.getIdFactura()).orElse(null);
    }

    @Override
    public void save(Factura factura) {
       facturaDao.save(factura);
    }

    @Override
    public void delete(Long idFactura) {
        facturaDao.deleteById(idFactura);
    }
    @Override
    public List<Factura> findByIdUsuario(Long idUsuario) {
       return facturaDao.encontrarPorUsuario(idUsuario);
    }
    
    @Override
    public List<Factura> findByIdUsuarioYEstado(Long idUsuario, String estado) {
       return facturaDao.encontrarPorUsuarioYEstado(idUsuario, estado);
    }
    @Override
    public List<Factura> findByEstado( String estado) {
       return facturaDao.encontrarEstado(estado);
    }
    
    @Override
    public Factura findByIdFactura(Long idFactura) {
        return facturaDao.findByIdFactura(idFactura);
    }

    @Override
    public Double findTotal() {
       return facturaDao.findTotal();
    }
}
