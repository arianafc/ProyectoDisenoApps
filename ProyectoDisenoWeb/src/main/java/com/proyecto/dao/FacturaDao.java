/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.dao;

import com.proyecto.domain.Factura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ariana
 */
public interface FacturaDao extends JpaRepository<Factura, Long>{
     @Query(nativeQuery = true,
            value = "SELECT * FROM factura where factura.id_usuario = :idUsuario")
    public List<Factura> encontrarPorUsuario(@Param("idUsuario") Long idUsuario);
    
    @Query(nativeQuery = true,
            value = "SELECT * FROM factura where factura.id_usuario = :idUsuario AND factura.estado = :estado")
    public List<Factura> encontrarPorUsuarioYEstado(@Param("idUsuario") Long idUsuario, @Param("estado") String estado);
    
    @Query(nativeQuery = true,
            value = "SELECT * FROM factura where factura.estado = :estado")
    public List<Factura> encontrarEstado(@Param("estado") String estado);
    
    public Factura findByIdFactura(Long idFactura);
    
}
