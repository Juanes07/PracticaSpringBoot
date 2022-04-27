package com.crud.democrud.repositories;

import com.crud.democrud.models.UsuarioModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);

    /**
     *  Peticion query para actualizar el nombre de usuario por medio del id
     * @param id Long haciendo referencia al id del usuario
     * @param nombre String haciendo referencia al nombre del usuario
     */
    @Modifying
    @Query("UPDATE UsuarioModel userM set userM.nombre = :nombre where userM.id =:id")
    public void actualizarNombreUsuario(
            @Param(value = "id") Long id,
            @Param(value = "nombre") String nombre
    );

}