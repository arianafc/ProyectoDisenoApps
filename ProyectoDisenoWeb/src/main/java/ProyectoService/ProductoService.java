/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProyectoService;

/**
 *
 * @author feru0
 */

import Proyecto.domain.Producto;
import java.util.List;

public interface ProductoService {

    // Se obtiene un listado de categorias en un List
    public List<Producto> getProductos();
    // Se obtiene un Categoria, a partir del id de un categoria  

    public Producto getProducto(Producto producto);

 
    public void save(Producto producto);
    

    public void delete(Producto producto);
}
