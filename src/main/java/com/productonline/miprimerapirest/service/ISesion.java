package com.productonline.miprimerapirest.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ISesion {

  @Procedure(procedureName = "InsertarNuevaSesion")
  String insertarNuevaSesion(String cedula);

  @Modifying
  @Query("UPDATE Sesion s SET s.fechaFin = CURRENT_TIMESTAMP, s.activa = 0 WHERE s.token = :token")
  void cerrarSesion(@Param("token") String token);

}
