package com.productonline.miprimerapirest.controller;
import com.productonline.miprimerapirest.model.dto.UsuarioDto;
import com.productonline.miprimerapirest.model.entity.Usuario;
import com.productonline.miprimerapirest.model.payload.MensajeResponse;
import com.productonline.miprimerapirest.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private IUsuario usuarioService;

    @PostMapping("usuario")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioSave = null;
        try {
            if (usuarioService.existsById(usuarioDto.getCedula()) == true) {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta crear ya existe!")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND
                );
            } else {
                usuarioSave = usuarioService.save(usuarioDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado correctamente")
                        .object(UsuarioDto.builder()
                                .cedula(usuarioSave.getCedula())
                                .nombre(usuarioSave.getNombre())
                                .apellido(usuarioSave.getApellido())
                                .mail(usuarioSave.getMail())
                                .password(usuarioSave.getPassword())
                                .usuario(usuarioSave.getUsuario())
                                .direccion(usuarioSave.getDireccion())
                                .telefono(usuarioSave.getTelefono())
                                .nacionalidad(usuarioSave.getNacionalidad())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            }

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("usuario/{cedula}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable String cedula) {
        Usuario usuarioUpdate = null;
        try {
            if (usuarioService.existsById(cedula)) {
                usuarioDto.setCedula(cedula);
                usuarioUpdate = usuarioService.save(usuarioDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado Correctamente")
                        .object(UsuarioDto.builder()
                                .cedula(usuarioUpdate.getCedula())
                                .nombre(usuarioUpdate.getNombre())
                                .apellido(usuarioUpdate.getApellido())
                                .mail(usuarioUpdate.getMail())
                                .password(usuarioUpdate.getPassword())
                                .usuario(usuarioUpdate.getUsuario())
                                .direccion(usuarioUpdate.getDireccion())
                                .telefono(usuarioUpdate.getTelefono())
                                .nacionalidad(usuarioUpdate.getNacionalidad())
                                .build())
                        .build(),
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la BD")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);

        }
    }

    @DeleteMapping("usuario/{cedula}")
    public ResponseEntity<?> delete(@PathVariable String cedula) {
        try {
            Usuario usuarioDelete = null;
            if (usuarioService.existsById(cedula)) {
                usuarioDelete = usuarioService.findById(cedula);
                System.out.println("-----ID del Usuario a Eliminar------ " + usuarioDelete.getCedula());
                usuarioService.delete(usuarioDelete);
                System.out.println("---PASE POR AQUI---");
                return ResponseEntity.status(204).body("El Registro se Eliminó Correctamente");
                //return new ResponseEntity<>("El Registro se Eliminó Correctamente", HttpStatus.NO_CONTENT);
                //MensajeResponse.builder()
//                    .mensaje("El Registro se Elimino correctamente")
//                    .object(null)
//                    .build()
//                    ,HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta Eliminar no se encuentra en la BD")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("usuario/{cedula}")
    public ResponseEntity<?> showById(@PathVariable String cedula) {
        Usuario usuario = usuarioService.findById(cedula);
        if (usuario == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta Exitosa!")
                        .object(UsuarioDto.builder()
                                .cedula(usuario.getCedula())
                                .nombre(usuario.getNombre())
                                .apellido(usuario.getApellido())
                                .mail(usuario.getMail())
                                .password(usuario.getPassword())
                                .usuario(usuario.getUsuario())
                                .direccion(usuario.getDireccion())
                                .telefono(usuario.getTelefono())
                                .nacionalidad(usuario.getNacionalidad())
                                .build())
                        .build()
                , HttpStatus.OK);
    }
}
