/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.dao;

/**
 *
 * @author feru0
 */
import com.proyecto.domain.Categoria;
import com.proyecto.domain.Estilo;
import com.proyecto.domain.Producto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Long> {

    public List<Producto> findByCategoria(Categoria categoria);

    public List<Producto> findByCategoriaAndEstilo(Categoria categoria, Estilo estilo);

    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);

    @Query(nativeQuery = true,
            value = "SELECT * FROM producto where producto.id_categoria = :idCategoria AND producto.marca = :marca ORDER BY producto.descripcion ASC")
    public List<Producto> filtrarMarcaYCategoria(@Param("idCategoria") Long idCategoria, @Param("marca") String marca);

    @Query(nativeQuery = true,
            value = "SELECT * FROM producto where producto.id_estilo = :idEstilo ORDER BY producto.descripcion ASC")
    public List<Producto> findByEstilo(@Param("idEstilo") Long idEstilo);

    @Query(nativeQuery = true,
            value = "SELECT * FROM producto where producto.marca = :marca ORDER BY producto.descripcion ASC")
    public List<Producto> findByMarca(@Param("marca") String marca);

    @Query(nativeQuery = true, value = "SELECT * FROM producto WHERE producto.precio BETWEEN :precioInf AND :precioSup ")
    List<Producto> findByFiltros(@Param("precioInf") double precioInf,
            @Param("precioSup") double precioSup);

    
   @Query(nativeQuery = true,
            value = "SELECT * FROM producto where producto.id_producto = :idProducto")
    public Producto findByIdProducto(@Param("idProducto") Long idProducto);
}
