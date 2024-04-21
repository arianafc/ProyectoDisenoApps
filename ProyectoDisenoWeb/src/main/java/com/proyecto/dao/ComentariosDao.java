/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.dao;

import com.proyecto.domain.Comentarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ariana
 */
public interface ComentariosDao extends JpaRepository<Comentarios, Long>{
    @Query(nativeQuery = true,
            value = "SELECT * FROM comentarios where comentarios.id_producto = :idProducto")
    public List<Comentarios> findByIdProducto(@Param("idProducto") Long idProducto);
}
