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
import com.proyecto.domain.Producto;
import com.proyecto.service.ListaDeseosService;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListaDeseosServiceImpl implements ListaDeseosService {

    @Autowired
    private ListaDeseosDao listaDeseosDao;

    @Autowired
    private ProductoService productoService;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerListaDeseosList(Long idUsuario) {
        return listaDeseosDao.findByIdUsuario(idUsuario)
                .map(ListaDeseos::getProductosDeseados)
                .orElse(new ArrayList<>());
    }

    @Override
    @Transactional
    public void agregarProductoDeseado(Long idUsuario, Long idProducto) {
                // Implementación pendiente.
    }

    @Override
    @Transactional
    public void eliminarProductoDeseado(Long idUsuario, Long idProducto) {
        ListaDeseos listaDeseos = listaDeseosDao.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Lista de deseos no encontrada"));
        final long idProductoPrimitivo = idProducto; 
        listaDeseos.getProductosDeseados().removeIf(p -> p.getIdProducto() == idProductoPrimitivo);
        listaDeseosDao.save(listaDeseos);
    }

    @Override
    @Transactional
    public void transferirProductoACarrito(Long idUsuario, Long idProducto) {
        // Implementación pendiente.
    }
}
