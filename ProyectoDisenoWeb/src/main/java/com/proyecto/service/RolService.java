/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Rol;
import java.util.List;

/**
 *
 * @author Ariana
 */
public interface RolService {
    public List<Rol> getRols();
 
    public Rol getRol(Rol rol);   
     
    public void saveRol(Rol rol);          
  
    public void delete(Long idRol);
}
