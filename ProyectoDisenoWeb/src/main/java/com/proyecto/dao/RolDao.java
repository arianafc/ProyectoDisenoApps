package com.proyecto.dao;

import com.proyecto.domain.Rol;
import com.proyecto.domain.Tallas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolDao extends JpaRepository<Rol, Long> {
     @Query(nativeQuery = true,
            value = "SELECT * FROM rol where rol.id_usuario = :idUsuario")
    public Rol findByIdUsuario(@Param("idUsuario") Long idUsuario);
}
