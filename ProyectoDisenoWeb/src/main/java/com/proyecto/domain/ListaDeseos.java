/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

/**
 *
 * @author jorge
 */
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "deseos")
public class ListaDeseos implements Serializable {    
    private static final long serialVersionUID = 1L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long idUsuario;
    private Long idProducto;
    private String nombreProducto;
    private String descripcion;
    private String marca;
    private String rutaImagen;
    private double precio;
    private String categoria;
    private String estilo;
    private int existencias;

    public ListaDeseos(Producto producto, Usuario usuario) {
        this.nombreProducto = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.marca = producto.getMarca();
        this.rutaImagen = producto.getRutaImagen();
        this.precio = producto.getPrecio();
        this.categoria = producto.getCategoria().getNombre();
        this.estilo = producto.getEstilo().getNombre();
        this.idProducto = producto.getIdProducto();
        this.idUsuario = usuario.getIdUsuario();
        this.existencias= producto.getExistencias();
    }

    public ListaDeseos() {
    }

}
