package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_lista_deseos")
    private ListaDeseos listaDeseos;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    private String nombre;
    private String marca;
    private String descripcion;
    private double precio;
    private int cantidad;
    private String status;
    private String rutaImagen;

    @ManyToOne //aqui se realiza la asociacion con la otra tabla
    @JoinColumn(name = "id_categoria")
    Categoria categoria;

    @ManyToOne //aqui se realiza la asociacion con la otra tabla
    @JoinColumn(name = "id_estilo")
    Estilo estilo;

    public Producto() {
    }

}
