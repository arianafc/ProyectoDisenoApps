/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Venta;
import java.util.List;

/**
 *
 * @author Ariana
 */
public interface VentaService {
    public List<Venta> getVentas();
 
    public Venta getVenta(Venta venta);   
     
    public void saveVenta(Venta venta);          
  
    public void delete(Long idVenta);
}
