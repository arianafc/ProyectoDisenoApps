/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Categoria;
import com.proyecto.domain.Estilo;
import com.proyecto.domain.Producto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ariana / Jorge
 */
@Service
public interface ProductoService {

    // Obtener todos los productos
    public List<Producto> getProductos();

    // Obtener un producto por su ID
    public Producto getProducto(Producto producto);

    // Guardar un nuevo producto o actualizar uno existente
    public void save(Producto producto);

    // Eliminar un producto
    public void deleteProducto(Long id);


    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    public List<Producto> findByCategoria(Categoria categoria);
    public List<Producto> findByCategoriaAndEstilo(Categoria categoria, Estilo estilo);
    public List<Producto> findByMarca(String marca);
    public List<Producto> filtrarMarcaYCategoria(Long idCategoria, String marca);
    public List<Producto> findByEstilo(Long idEstilo);
    public List<Producto> findByFiltros(double precioInf, double precioSup);
    public Producto findByIdProducto(Long idProducto);
}
