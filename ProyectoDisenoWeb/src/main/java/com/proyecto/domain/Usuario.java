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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
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
    private int cedula;
    private String username;
    private String apellidos;
     private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private String rutaImagen;
    private boolean activo;

    public long getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public Usuario(long idUsuario, int cedula, String username, String email, String apellidos, String password, String nombre, String telefono, String direccion, String rutaImagen, boolean activo) {
        this.idUsuario = idUsuario;
        this.cedula = cedula;
        this.username = username;
        this.apellidos = apellidos;
        this.nombre=nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }

    public Usuario() {
    }

    @OneToMany
    @JoinColumn(name = "id_usuario")
    List<Rol> roles;

}
