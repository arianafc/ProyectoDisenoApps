package com.proyecto.dao;

import com.proyecto.domain.Categoria;


import org.springframework.data.jpa.repository.JpaRepository;
 
public interface CategoriaDao extends JpaRepository <Categoria,Long> { //long = id
    //crea una capa de datos que extiende a la clase creada, es decir, jala la info de los atributos

}