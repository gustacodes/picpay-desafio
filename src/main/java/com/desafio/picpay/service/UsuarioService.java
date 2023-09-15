package com.desafio.picpay.service;

import com.desafio.picpay.entitie.Usuario;
import com.desafio.picpay.exception.Usuarios.CpfCadastrado;
import com.desafio.picpay.exception.Usuarios.EmailCadastrado;
import com.desafio.picpay.repositorie.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public String save(Usuario usuario) {

        if (repository.existsByCpf(usuario.getCpf())) {
            try {
                throw new CpfCadastrado("CPF já cadastrado no sistema");
            } catch (CpfCadastrado e) {
                return e.getMessage();
            }

        } else if (repository.existsByEmail(usuario.getEmail())) {
            try {
                throw new EmailCadastrado("E-mail já cadastrado no sistema.");
            } catch (EmailCadastrado e) {
                return e.getMessage();
            }

        }

        return "Usuário cadastrado.";
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.get();
    }
}
