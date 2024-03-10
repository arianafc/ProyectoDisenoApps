/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Admin;
import com.proyecto.domain.Producto;
import java.util.List;

/**
 *
 * @author feru0
 */
public interface AdminService {
    // Obtener todos los administradores
    public List<Admin> getAdmins();
    
    // Obtener un admin por su ID
    public Admin getAdmin(Long id);
    
    // Guardar un nuevo administrador o actualizar uno existente
    public void saveAdmin(Admin admin);
    
    // Eliminar un administrador
    public void deleteAdmin(Long id);
}
