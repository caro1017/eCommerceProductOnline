package com.productonline.miprimerapirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}