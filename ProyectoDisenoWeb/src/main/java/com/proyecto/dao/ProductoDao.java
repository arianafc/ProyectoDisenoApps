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
public interface ProductoDao extends JpaRepository <Producto,Long> {

    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByCategoriaAndEstilo(Categoria categoria, Estilo estilo);
    List<Producto> findByCategoriaAndMarca(Categoria categoria, String marca);
}