package com.desafio.picpay.exception.Transacoes;

import org.springframework.stereotype.Component;

public class SaldoInsuficiente extends Exception {

    public SaldoInsuficiente(String mensagem) {
        super(mensagem);
    }
}
