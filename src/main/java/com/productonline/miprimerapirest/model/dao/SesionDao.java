package com.productonline.miprimerapirest.model.dao;

import com.productonline.miprimerapirest.model.entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SesionDao extends CrudRepository<Usuario,String> {

    @Procedure(procedureName = "InsertarNuevaSesion")
    String insertarNuevaSesion(@Param("Cedula") String cedula);
    //void insertarNuevaSesion(String cedula);

    @Modifying
    @Query("UPDATE Sesion s SET s.fechaFin = CURRENT_TIMESTAMP, s.activa = 0 WHERE s.token = :token")
    void cerrarSesion(@Param("token") String token);
}
