package com.productonline.miprimerapirest.controller;

import com.productonline.miprimerapirest.exception.ProductoNoEncontradoException;
import com.productonline.miprimerapirest.model.payload.MensajeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<MensajeResponse> handleProductoNoEncontradoException(ProductoNoEncontradoException ex) {
        //return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("El registro que intenta eliminar no Existe!")
                        .object(null)
                        .build()
                , HttpStatus.NOT_FOUND
        );
    }
}
