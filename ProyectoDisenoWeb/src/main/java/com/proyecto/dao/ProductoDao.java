/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.dao;

/**
 *
 * @author feru0
 */
import com.proyecto.domain.Producto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 @Repository
public interface ProductoDao extends JpaRepository <Producto,Long> {
     public List<Producto> findByEstiloAndCategoria(String estilo, String categoria);
}