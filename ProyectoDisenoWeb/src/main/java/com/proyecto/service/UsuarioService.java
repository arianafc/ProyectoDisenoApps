/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Rol;
import com.proyecto.domain.Usuario;
import java.util.List;

/**
 *
 * @author fallaa8
 */
public interface UsuarioService {
    public List<Usuario> getUsuarios();
    // Se obtiene un Categoria, a partir del id de un categoria     
    public Usuario getUsuario(Usuario usuario);   
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío    
    // Se actualiza un categoria si el id del categoria NO esta vacío     
    public void save(Usuario usuario,boolean crearRolUser);         
    
    public void saveUsuario(Usuario usuario);
    
// Se elimina el categoria que tiene el id pasado por parámetro     
    public void delete(Long idUsuario); 
    
    public Usuario getUsuarioporEmail(String email);
    
    public Usuario getUsuarioPorEmailYPassword(String email, String password);
    
    public boolean existeUsuarioPorEmail(String email);
    
    
    public Usuario getUsuarioPorUsername(String username);

    
    public Usuario getUsuarioPorUsernameYPassword(String username, String password);
    
   
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo);
    
  
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo);
}