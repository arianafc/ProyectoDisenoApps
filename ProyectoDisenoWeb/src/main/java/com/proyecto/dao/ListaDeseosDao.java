/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.dao;
/**
 *
 * @author jorge
 */
import com.proyecto.domain.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ListaDeseosDao extends JpaRepository<ListaDeseos, Long> {
    Optional<ListaDeseos> findByIdUsuario(Long idUsuario);
}
