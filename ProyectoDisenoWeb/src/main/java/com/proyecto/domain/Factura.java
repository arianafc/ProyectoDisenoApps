package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="factura")

public class Factura implements Serializable {    
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_factura")
    private Long idFactura;
    private Long idUsuario;
    private LocalDate fecha;
    private double total;
    private String estado;
    private String direccion;
    private String metodoPago;
    private String rutaImagen;
    
    public Factura() {
    }

    public Factura(Long idUSuario, String direccion, String metodoPago) throws ParseException {
        this.idUsuario = idUSuario;
        this.fecha =LocalDate.now();
        this.estado="Nueva Orden";
        this.direccion=direccion;
        this.metodoPago=metodoPago;
    }    
}