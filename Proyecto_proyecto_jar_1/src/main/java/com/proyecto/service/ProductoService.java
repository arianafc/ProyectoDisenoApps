/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Producto;
import java.util.List;

/**
 *
 * @author Ariana / Jorge
 */

public interface ProductoService {
    // Obtener todos los productos
    public List<Producto> getProductos();
    
    // Obtener un producto por su ID
    public Producto getProducto(Long id);
    
    // Guardar un nuevo producto o actualizar uno existente
    public void saveProducto(Producto producto);
    
    // Eliminar un producto
    public void deleteProducto(Long id);
}
