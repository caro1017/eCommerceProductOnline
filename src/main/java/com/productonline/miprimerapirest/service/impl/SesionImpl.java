package com.productonline.miprimerapirest.service.impl;

import com.productonline.miprimerapirest.model.dao.SesionDao;
import com.productonline.miprimerapirest.service.ISesion;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service
public class SesionImpl implements ISesion {

    @Autowired
    private SesionDao sesionDao;

    @Transactional
    @Override
    public String insertarNuevaSesion(String cedula) {
        try{
            return sesionDao.insertarNuevaSesion(cedula);
        }catch (DataIntegrityViolationException | JpaSystemException | PersistenceException e)
        {
            throw new CustomDatabaseException("Error al crear sesión: El usuario no existe o hay un conflicto con la clave foránea.", e);
        }
    }

    @Transactional
    @Override
    public void cerrarSesion(String token) {

        try{
            sesionDao.cerrarSesion(token);
        }catch (DataIntegrityViolationException | JpaSystemException | PersistenceException e)
        {
            throw new CustomDatabaseException("Error al crear sesión: El usuario no existe o hay un conflicto con la clave foránea.", e);
        }

    }

    @ExceptionHandler(CustomDatabaseException.class)
    public ResponseEntity<String> handleCustomDatabaseException(CustomDatabaseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
