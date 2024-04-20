/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.dao;

import com.proyecto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository <Usuario,Long>   {
    Usuario findByEmail(String email);
    
    Usuario findByEmailAndPassword(String email, String Password);

    boolean existsByEmail(String email);
    
    Usuario findByUsername(String username);
    
    Usuario findByUsernameAndPassword(String username, String Password);

    Usuario findByUsernameOrEmail(String username, String correo);

    boolean existsByUsernameOrEmail(String username, String correo);
    
    @Query("SELECT max(u.idUsuario) FROM Usuario u")
    Long getLastId();
}
