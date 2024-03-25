/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Estilo;
import java.util.List;

/**
 *
 * @author Ariana
 */
public interface EstiloService {
    public List<Estilo> getEstilos();
 
    public Estilo getEstilo(Estilo estilo);   
     
    public void save(Estilo categoria);          
  
    public void delete(Estilo categoria); 
    public Estilo getEstiloByName(String name);
}
