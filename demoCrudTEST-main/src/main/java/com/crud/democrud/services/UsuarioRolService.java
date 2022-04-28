package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioRolService {
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    public UsuarioRolModel guardarRol(UsuarioRolModel rolModel) {
        return usuarioRolRepository.save(rolModel);
    }

    /**
     *
     * @Transactional inyeccion para indicar que solo es una peticion de lectura sin afectar la base
     * @return
     */
    @Transactional(readOnly = true)
    public List<UsuarioRolModel> listaDeRoles() {
        return (List<UsuarioRolModel>) usuarioRolRepository.findAll();
    }


    public UsuarioRolModel actualizarRol(long id, UsuarioRolModel usuarioRolModel) {
        usuarioRolModel.setId(id);
        return usuarioRolRepository.save(usuarioRolModel);
    }

    public Boolean eliminarRolUsuario(long id) {
        try {
            usuarioRolRepository.deleteById(id);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }
}
