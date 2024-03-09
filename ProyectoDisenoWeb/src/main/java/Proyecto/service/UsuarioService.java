/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto.service;

import Proyecto.domain.Usuario;
import java.util.List;

/**
 *
 * @author fallaa8
 */
public interface UsuarioService {
    public List<Usuario> getUsuarios();
    // Se obtiene un Categoria, a partir del id de un categoria     
    public Usuario getUsuario(String email);   
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío    
    // Se actualiza un categoria si el id del categoria NO esta vacío     
    public void save(Usuario usuario);          
    
// Se elimina el categoria que tiene el id pasado por parámetro     
    public void delete(Usuario usuario); 
    
    
}