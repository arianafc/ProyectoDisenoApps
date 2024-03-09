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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //id categoria es la llave de la tabla categoria. Aqui va el primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Los valores generados que estrategia usan, identico a la BD 
    @Column(name = "id_usuario") 
    private long idUsuario;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private String rol;
    
    
    public long getIdUsuario(){
        return idUsuario;
    }
    
    public String getEmail(){
        return email;
    }

    public Usuario(long idUsuario, String nombre, String email, String password, String telefono, String direccion, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
    }

    public Usuario() {
    }
    
    
    
}
