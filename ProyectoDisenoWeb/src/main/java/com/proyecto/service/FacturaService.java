/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Factura;
import java.util.List;

/**
 *
 * @author Ariana
 */
public interface FacturaService {
    public List<Factura> getRols();
 
    public Factura getFactura(Factura factura);   
     
    public void save(Factura factura);          
  
    public void delete(Long idFactura);
    
    public List<Factura> findByIdUsuario(Long idUsuario);
    public List<Factura> findByIdUsuarioYEstado(Long idUsuario, String estado);
    public List<Factura> findByEstado(String estado);
    public Factura findByIdFactura(Long idFactura);
    
}
