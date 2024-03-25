/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

/**
 *
 * @author Jorge
 */
import com.proyecto.dao.ProductoDao;
import com.proyecto.domain.Categoria;
import com.proyecto.domain.Estilo;
import com.proyecto.domain.Producto;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void deleteProducto(Long id) {
        productoDao.deleteById(id);
    }

    
    @Transactional(readOnly = true)
    public List<Producto> findByCategoria(Categoria categoria) {
        return productoDao.findByCategoria(categoria);
    }

   @Transactional(readOnly = true)
    public List<Producto> findByCategoriaAndEstilo(Categoria categoria, Estilo estilo) {
        return productoDao.findByCategoriaAndEstilo(categoria, estilo);
    }
    
    @Transactional(readOnly = true)
    public List<Producto> findByCategoriaAndMarca(Categoria categoria, String marca) {
        return productoDao.findByCategoriaAndMarca(categoria, marca);
    }

}
