/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

/**
 *
 * @author jorge
 */
import com.proyecto.domain.ListaDeseos;
import java.util.List;

public interface ListaDeseosService {
    // Este método ahora reflejará la obtención de todas las listas de deseos sin filtrar por usuario
    List<ListaDeseos> obtenerTodasLasListasDeseos();

    void agregarProductoDeseado(Long idUsuario, Long idProducto);

    void eliminarProductoDeseado(Long idUsuario, Long idProducto);

    void transferirProductoACarrito(Long idUsuario, Long idProducto);
}
