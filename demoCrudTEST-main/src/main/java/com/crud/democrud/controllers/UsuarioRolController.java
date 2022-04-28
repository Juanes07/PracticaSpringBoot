package com.crud.democrud.controllers;


import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.services.UsuarioRolService;
import com.crud.democrud.utility.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ValueExp;
import javax.xml.catalog.Catalog;
import java.sql.SQLException;


@CrossOrigin
@RestController
@RequestMapping("/rol")
public class UsuarioRolController {
    @Autowired
    private UsuarioRolService usuarioRolService;

    private final Respuesta response = new Respuesta();
    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping("/listar")
    public ResponseEntity<Respuesta> listaDeRoles(){
            response.Reiniciar();
        try {
            response.data = usuarioRolService.listaDeRoles();
            httpStatus = HttpStatus.OK;
        } catch (Exception exc){
            getErrorMessageInternal(exc);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Respuesta> guardarRol(@RequestBody UsuarioRolModel usuarioRolModel){
        response.Reiniciar();
        try{
            response.data = usuarioRolService.guardarRol(usuarioRolModel);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return  new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Respuesta> actualizarRolUsuario(@PathVariable(value = "id") Long id, @RequestBody UsuarioRolModel usuarioRolModel){
        response.Reiniciar();
        try {
            response.data = usuarioRolService.actualizarRol(id,usuarioRolModel);
            httpStatus = HttpStatus.OK;
        } catch (Exception exc){
            getErrorMessageInternal(exc);
        }
        return  new ResponseEntity<>(response, httpStatus);
    }


    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<Respuesta> borrarRolUsuario(@PathVariable(value = "id") Long id){
        response.Reiniciar();
        try {
            response.data = usuarioRolService.eliminarRolUsuario(id);
            if(response.data == null){
                response.mensaje = "El rol no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.mensaje =" EL rol fue eliminado";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if (exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.mensaje = "El dato ya está registrado";
                    break;
                case 1452:
                    response.mensaje = "El usuario indicado no existe";
                    break;
                default:
                    response.mensaje = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.mensaje = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.mensaje = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
