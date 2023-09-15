package com.desafio.picpay.service;

import com.desafio.picpay.entitie.Usuario;
import com.desafio.picpay.exception.Transacoes.FalhaTransferencia;
import com.desafio.picpay.exception.Transacoes.SaldoInsuficiente;
import com.desafio.picpay.repositorie.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoService {

    private UsuarioRepository repository;

    public TransacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String transaction(Usuario payer, Usuario payee, Double valor) {

        try {

            if (payer.getTipo().name().equalsIgnoreCase("LOJISTA")) {
                throw new FalhaTransferencia("Lojista não pode fazer transferências.");
            }

            if (payer.getSaldo() > 0 && payer.getSaldo() >= valor) {

                payee.setSaldo(payee.getSaldo() + valor);
                payer.setSaldo(payer.getSaldo() - valor);
                repository.save(payer);
                repository.save(payee);

            }

            return "Transferência realizada";

        } catch (FalhaTransferencia e) {
            return e.getMessage();
        }
    }

}
