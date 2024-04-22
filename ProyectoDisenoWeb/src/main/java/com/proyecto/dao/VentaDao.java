/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.dao;

import com.proyecto.domain.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ariana
 */
public interface VentaDao extends JpaRepository<Venta, Long>{
    @Query(nativeQuery = true,
            value = "SELECT * FROM venta where venta.id_factura = :idFactura")
    public List<Venta> encontrarPorUsuario(@Param("idFactura") Long idFactura);
}
