/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.dao;

import com.proyecto.domain.Producto;
import com.proyecto.domain.Tallas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ariana
 */
public interface TallasDao extends JpaRepository<Tallas, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM tallas where tallas.id_producto = :idProducto")
    public List<Tallas> encontrarPorID(@Param("idProducto") Long idProducto);

}
