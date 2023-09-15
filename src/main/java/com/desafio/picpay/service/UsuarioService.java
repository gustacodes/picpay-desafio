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

    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public String save(Usuario usuario) {
        repository.save(usuario);
        return "Usuário cadastrado.";
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.get();
    }
}
