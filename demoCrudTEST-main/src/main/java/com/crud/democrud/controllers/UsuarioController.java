package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.services.UsuarioRolService;
import com.crud.democrud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Lista de usuarios
     * @return respuest Http con el valor OK si fue satisfactorio, caso contrario error del server
     */
    @GetMapping("/lista")
    public ResponseEntity<ArrayList<UsuarioModel>>  listaUsuarios(){
        ArrayList<UsuarioModel> listaDeUsuarios = usuarioService.obtenerUsuarios();
        try {
            return  new ResponseEntity<>(listaDeUsuarios, HttpStatus.OK);
        } catch (Exception exp){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Crear usuario
     * @param usuarioModel
     * @return
     */
    @PostMapping("/crear")
    public ResponseEntity<UsuarioModel> crearUsuario(@RequestBody UsuarioModel usuarioModel){
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuarioModel),HttpStatus.CREATED);
    }


    @PutMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable("id") long id, @RequestBody UsuarioModel usuarioModel){
        Optional<UsuarioModel> obtenerUsuario = usuarioService.obtenerPorId(id);
        if (obtenerUsuario.isEmpty()){
            return "No existe el usuario que quieres actualizar" + id;
        }
        usuarioService.actualizarUsuario(id, usuarioModel);
        return "usuario actualizado";

    }

    @GetMapping("listaPrioridad/{prioridad}")
    public ResponseEntity<?> listaUsuariosPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        try {
            return new ResponseEntity<>(usuarioService.obtenerPorPrioridad(prioridad), HttpStatus.OK);
        } catch (Exception exc){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuarioPorId(@PathVariable("id") Long id){
        Optional<UsuarioModel> usuarioEncontrado = usuarioService.obtenerPorId(id);
        if(usuarioEncontrado.isPresent()){
            usuarioService.eliminarUsuario(id);
            return  new ResponseEntity<>("Eliminado correctamente",HttpStatus.OK);
        }
        return  new ResponseEntity<>("No existe el usuario a eliminar", HttpStatus.NOT_FOUND);
    }
}