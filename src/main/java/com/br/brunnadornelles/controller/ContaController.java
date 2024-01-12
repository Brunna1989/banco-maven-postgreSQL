package com.br.brunnadornelles.controller;

import com.br.brunnadornelles.model.Conta;
import com.br.brunnadornelles.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/sacar")
    public ResponseEntity<String> sacar(@RequestParam String numeroConta, @RequestParam BigDecimal valor) {
        try {
            contaService.sacar(numeroConta, valor);
            return ResponseEntity.ok("Saque realizado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/depositar")
    public ResponseEntity<String> depositar(@RequestParam String numeroConta, @RequestParam BigDecimal valor) {
        try {
            contaService.depositar(numeroConta, valor);
            return ResponseEntity.ok("Depósito realizado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/extrato")
    public ResponseEntity<List<Conta>> extrato() {
        List<Conta> extrato = contaService.gerarExtrato();
        return ResponseEntity.ok(extrato);
    }
}
