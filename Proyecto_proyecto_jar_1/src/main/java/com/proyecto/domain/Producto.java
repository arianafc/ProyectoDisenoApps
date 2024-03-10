package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private long idProducto;
    
    @Column(name = "nombre_producto")
    private String nombreProducto;
    
     @Column(name = "status")
    private String status;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "precio")
    private double precio;
    
    @Column(name = "cantidad")
    private int cantidad;
    
    @Column(name = "categoria")
    private String categoria;
    
    @Column(name = "imagen")
    private String imagen;

    public Producto() {
    }

    public Producto(String nombreProducto, String descripcion, double precio, int stock, String categoria, String imagen) {
        this.nombreProducto = nombreProducto;
        this.status = status;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.imagen = imagen;
    }
}
