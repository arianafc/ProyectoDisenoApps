/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Proyecto.service;

import Proyecto.domain.Producto;
import Proyecto.domain.Usuario;
import java.util.List;

/**
 *
 * @author Ariana
 */
public interface ProductoService {
     public List<Producto> getProductos();
    // Se obtiene un Categoria, a partir del id de un categoria     
    public Usuario getProductos(Producto producto);   
    
    // Se inserta un nuevo categoria si el id del categoria esta vacío    
    // Se actualiza un categoria si el id del categoria NO esta vacío     
    public void save(Producto producto);          
    
// Se elimina el categoria que tiene el id pasado por parámetro     
    public void delete(Producto producto); 
}
