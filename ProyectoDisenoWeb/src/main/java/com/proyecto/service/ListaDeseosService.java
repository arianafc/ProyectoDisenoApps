/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;
/**
 *
 * @author jorge
 */
import com.proyecto.domain.Producto;
import java.util.List;

public interface ListaDeseosService {
    List<Producto> obtenerListaDeseosList(Long idUsuario);
    void agregarProductoDeseado(Long idUsuario, Long idProducto);
    void eliminarProductoDeseado(Long idUsuario, Long idProducto);
    void transferirProductoACarrito(Long idUsuario, Long idProducto);
}