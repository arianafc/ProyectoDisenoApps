/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto {
    private int cantidad; //Almacenar la cantidad de items de un producto
    private double talla;
    private String color;
    
    public Item() {
    }

    public Item(Producto producto, double talla, String color) {
        super.setNombre(producto.getNombre());
        super.setIdProducto(producto.getIdProducto());
        super.setMarca(producto.getMarca());
        super.setCategoria(producto.getCategoria());
        super.setDescripcion(producto.getDescripcion());
        super.setEstilo(producto.getEstilo());
        super.setPrecio(producto.getPrecio());
        super.setExistencias(producto.getExistencias());
        super.setStatus(producto.getStatus());
        super.setRutaImagen(producto.getRutaImagen());
        this.cantidad = 0;
        this.talla=talla;
        this.color=color;
    }
}