package com.desafio.picpay.controller;

import com.desafio.picpay.entitie.Usuario;
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

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/transaction")
    public ResponseEntity<String> transaction(@RequestParam("valor") Double valor, @RequestParam("payer") Long payer,
                                              @RequestParam("payee") Long payee) {

        Usuario usuarioPayer = usuarioService.findById(payer);
        Usuario usuarioPayee = usuarioService.findById(payee);

        var transaction = transactionService.transaction(usuarioPayer, usuarioPayee, valor);

        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

}
