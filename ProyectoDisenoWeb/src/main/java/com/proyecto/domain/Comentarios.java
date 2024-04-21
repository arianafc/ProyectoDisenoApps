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
import java.util.Date;
import lombok.Data;


@Data
@Entity
@Table(name = "comentarios")
public class Comentarios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name = "id_comentario")
    private Long idComentario;
    private String comentario;
    private double talla;
    private String color;
    private Date fecha;
    private String usuario;
    private String nombreProducto;

    public Comentarios() {
    }

    public Comentarios(Long idComentario, String comentario, double talla, String color, Date fecha, String usuario, String nombreProducto, Long idProducto, Long idUsuario) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.talla = talla;
        this.color = color;
        this.fecha = fecha;
        this.usuario = usuario;
        this.nombreProducto = nombreProducto;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
    }

   

    
   
    
    @Column (name = "id_producto")
    private Long idProducto;
    
    @Column (name="id_usuario")
    private Long idUsuario;
}
