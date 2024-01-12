package com.br.brunnadornelles.service;

import com.br.brunnadornelles.model.Conta;
import com.br.brunnadornelles.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public void depositar(String numeroConta, BigDecimal valor) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta);
        if (conta == null) {
            throw new RuntimeException("Conta inexistente");
        }

        BigDecimal novoSaldo = conta.getSaldo().add(valor);
        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);
    }

    @Transactional
    public void sacar(String numeroConta, BigDecimal valor) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta);
        if (conta == null) {
            throw new RuntimeException("Conta inexistente");
        }

        BigDecimal novoSaldo = conta.getSaldo().subtract(valor);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo insuficiente para saque!!!");
        }

        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);
    }

    public List<Conta> gerarExtrato() {
        return contaRepository.findAll();
    }
}



