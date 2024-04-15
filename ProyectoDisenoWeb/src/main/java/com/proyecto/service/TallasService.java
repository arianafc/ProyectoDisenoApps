/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Producto;
import com.proyecto.domain.Tallas;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface TallasService {
    public List<Tallas> findByidProducto(Long idProducto);
}
