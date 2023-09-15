package com.desafio.picpay.controller;

import com.desafio.picpay.entitie.Usuario;
import com.desafio.picpay.exception.Usuarios.CpfCadastrado;
import com.desafio.picpay.exception.Usuarios.EmailCadastrado;
import com.desafio.picpay.service.TransacaoService;
import com.desafio.picpay.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private TransacaoService transactionService;
    private UsuarioService usuarioService;

    public UsuarioController(TransacaoService service, UsuarioService usuarioService) {
        this.transactionService = service;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<String> save(@RequestBody Usuario usuario) {

        if (usuarioService.existsByCpf(usuario.getCpf())) {
            try {
                throw new CpfCadastrado("CPF já cadastrado no sistema");
            } catch (CpfCadastrado e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }

        } else if (usuarioService.existsByEmail(usuario.getEmail())) {
            try {
                throw new EmailCadastrado("E-mail já cadastrado no sistema.");
            } catch (EmailCadastrado e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/transaction")
    public ResponseEntity<String> transaction(@RequestParam("valor") Double valor, @RequestParam("payer") Long payer,
                                              @RequestParam("payee") Long payee) {

        Usuario usuarioPayer = usuarioService.findById(payer);
        Usuario usuarioPayee = usuarioService.findById(payee);

        if (valor > usuarioPayer.getSaldo()) {
            return ResponseEntity.badRequest().body("Saldo insuficiente");
        }

        var transacao = transactionService.transaction(usuarioPayer, usuarioPayee, valor);

        return ResponseEntity.status(HttpStatus.OK).body(transacao);
    }

}
