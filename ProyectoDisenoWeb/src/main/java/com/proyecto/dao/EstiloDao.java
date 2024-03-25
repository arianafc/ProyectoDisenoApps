package com.proyecto.dao;

import com.proyecto.domain.Estilo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstiloDao extends JpaRepository<Estilo, Long> { //long = id
    //crea una capa de datos que extiende a la clase creada, es decir, jala la info de los atributos

    Estilo findByNombre(String nombre);
}
