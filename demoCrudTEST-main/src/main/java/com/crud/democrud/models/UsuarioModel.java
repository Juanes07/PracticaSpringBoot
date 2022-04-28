package com.crud.democrud.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    private String nombre;


    private String email;


    private Integer prioridad;
    /**
     * Relacion a la tabla rol
     */
    @OneToMany(
            mappedBy = "usuarioModel",
            targetEntity = UsuarioRolModel.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE
    )
    @JsonManagedReference
    private List<UsuarioRolModel> usuarioRols;

    /**
     * Constructor
     * @param nombre
     * @param email
     * @param prioridad
     */
    public UsuarioModel(String nombre, String email, Integer prioridad) {
        this.nombre = nombre;
        this.email = email;
        this.prioridad = prioridad;
    }
}