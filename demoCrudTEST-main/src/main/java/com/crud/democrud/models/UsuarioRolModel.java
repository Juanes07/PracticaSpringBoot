package com.crud.democrud.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "usuario_rol")
@Entity
public class UsuarioRolModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rol;

    @ManyToOne(
            targetEntity = UsuarioModel.class,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "rol_usuarioid")
    @JsonBackReference
    private UsuarioModel usuarioModel;


    public UsuarioRolModel(String nombreRol, UsuarioModel usuarioModel) {
        this.rol = nombreRol;
        this.usuarioModel = usuarioModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
