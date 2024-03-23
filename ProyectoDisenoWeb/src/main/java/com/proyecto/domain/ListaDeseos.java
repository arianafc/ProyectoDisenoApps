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
@Table(name = "lista_deseos")
public class ListaDeseos implements Serializable {
       
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista_deseos")
    private Long idListaDeseos;
    
    @Column(name = "id_usuario")
    private Long idUsuario; // Suponiendo que tienes una entidad de Usuario.
    
    @ManyToMany
    @JoinTable(
        name = "lista_deseos_productos",
        joinColumns = @JoinColumn(name = "id_lista_deseos"),
        inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
    private List<Producto> productosDeseados;

    public ListaDeseos() {
    }

    public ListaDeseos(Long idListaDeseos, Long idUsuario, List<Producto> productosDeseados) {
        this.idListaDeseos = idListaDeseos;
        this.idUsuario = idUsuario;
        this.productosDeseados = productosDeseados;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
   
}
