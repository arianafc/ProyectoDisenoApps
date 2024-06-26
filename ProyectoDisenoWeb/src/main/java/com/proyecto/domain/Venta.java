/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="venta")
public class Venta implements Serializable {    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long idVenta;
    private Long idFactura;
    private Long idProducto;    
    private double precio;
    private int cantidad;    
    private String nombreProducto;
    private String nombreUsuario;
    private String rutaImagen;
    
    public Venta() {
    }

    public Venta(Long idFactura, Long idProducto, double precio, int cantidad, String nombreProducto, String nombreUsuario, String rutaImagen) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.nombreProducto=nombreProducto;
        this.nombreUsuario=nombreUsuario;
        this.rutaImagen=rutaImagen;
    }
}