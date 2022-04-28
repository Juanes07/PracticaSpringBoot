package com.crud.democrud.ServicesTest;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.UsuarioRepository;
import com.crud.democrud.repositories.UsuarioRolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRolServiceTest {

    @Autowired
    UsuarioRolRepository usuarioRolRepository;


    @Test
    public void testTraerRolesUsuarios() {
        List<UsuarioRolModel> listaRolesUsuarios = (List<UsuarioRolModel>) usuarioRolRepository.findAll();
        assertThat(listaRolesUsuarios).size().isPositive();
    }

    @Test
    public void testEncontrolPorRol() {
        Long id = 1L;
        Optional<UsuarioRolModel> rolUsuarioEncontrado = usuarioRolRepository.findById(id);
        assertThat(rolUsuarioEncontrado.get().getId()).isEqualTo(id);
    }

}
