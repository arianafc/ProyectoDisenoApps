package com.proyecto.domain;
/**
 *
 * @author Feru0110
 */

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "Administrador")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private long idAdministrador;
    
    @Column(name = "salario")
    private double salario;
    
    @Column(name = "rol")
    private String rol;
    

    public Admin() {
    }

    public Admin(long idAdministrador, double salario, String rol) {
        this.idAdministrador = idAdministrador;
        this.salario = salario;
        this.rol = rol;
    }





}
