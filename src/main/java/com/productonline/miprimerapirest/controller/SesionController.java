package com.productonline.miprimerapirest.controller;

import com.productonline.miprimerapirest.exception.UsuarioNoEncontradoException;
import com.productonline.miprimerapirest.model.dto.SesionDto;
import com.productonline.miprimerapirest.model.dto.SesionResponseDto;
import com.productonline.miprimerapirest.model.entity.Usuario;
import com.productonline.miprimerapirest.service.ISesion;
import com.productonline.miprimerapirest.service.IUsuario;
import com.productonline.miprimerapirest.service.impl.CustomDatabaseException;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SesionController {

    @Autowired
    private ISesion sesionService;

    @Autowired
    private IUsuario usuarioService;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody SesionDto loginRequest) {
        try {
            // Buscar usuario por su nombre de usuario
            Usuario usuario = usuarioService.findByUsuario(loginRequest.getUsuario());
            // Validar la contraseña (versión básica, sin cifrar)
            if (!usuario.getPassword().equals(loginRequest.getPassword())) {
                return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña o Usuario incorrectos");
            }
            String token = sesionService.insertarNuevaSesion(usuario.getCedula());

            SesionResponseDto response = new SesionResponseDto();
            response.setUsuario(usuario.getUsuario());
            response.setMensaje("Sesión iniciada exitosamente");
            response.setToken(token);
            return ResponseEntity.ok(response);

//            String token = sesionService.insertarNuevaSesion(usuario.getCedula());
//            Map<String, String> response = new HashMap<>();
//            response.put("mensaje", "Sesión iniciada exitosamente");
//            response.put("token", token);
//            response.put("usuario", usuario.getUsuario());
//            return ResponseEntity.ok(response.toString());
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (CustomDatabaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("cerrarSesion")
    public ResponseEntity<String> cerrarSesion(@RequestParam String token) {
        try {
            sesionService.cerrarSesion(token);
            return ResponseEntity.ok("Sesión cerrada exitosamente.");
        } catch (JpaSystemException | PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cerrar la sesión.");
        }
    }
}
