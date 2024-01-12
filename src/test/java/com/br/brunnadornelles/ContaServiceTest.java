package com.br.brunnadornelles;

import com.br.brunnadornelles.model.Conta;
import com.br.brunnadornelles.repository.ContaRepository;
import com.br.brunnadornelles.model.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDepositar() {
        Conta conta = new Conta();
        conta.setNumeroConta("12345");
        conta.setSaldo(BigDecimal.valueOf(1000));
        when(contaRepository.findByNumeroConta("12345")).thenReturn(conta);
        contaService.depositar("12345", BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(1500), conta.getSaldo());
        verify(contaRepository, times(1)).save(conta);
    }

    @Test
    void testSacarComSaldoSuficiente() {
        Conta conta = new Conta();
        conta.setNumeroConta("67890");
        conta.setSaldo(BigDecimal.valueOf(1000));
        when(contaRepository.findByNumeroConta("67890")).thenReturn(conta);
        contaService.sacar("67890", BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500), conta.getSaldo());
        verify(contaRepository, times(1)).save(conta);
    }

    @Test
    void testSacarComSaldoInsuficiente() {
        Conta conta = new Conta();
        conta.setNumeroConta("11111");
        conta.setSaldo(BigDecimal.valueOf(100));
        when(contaRepository.findByNumeroConta("11111")).thenReturn(conta);
        assertThrows(RuntimeException.class, () -> contaService.sacar("11111", BigDecimal.valueOf(500)));
        assertEquals(BigDecimal.valueOf(100), conta.getSaldo());
        verify(contaRepository, never()).save(any());
    }

    @Test
    void testGerarExtrato() {
        Conta conta1 = new Conta();
        conta1.setNumeroConta("111");
        conta1.setSaldo(BigDecimal.valueOf(1000));

        Conta conta2 = new Conta();
        conta2.setNumeroConta("222");
        conta2.setSaldo(BigDecimal.valueOf(2000));

        when(contaRepository.findAll()).thenReturn(List.of(conta1, conta2));
        List<Conta> extrato = contaService.gerarExtrato();
        assertEquals(2, extrato.size());
        assertEquals("111", extrato.get(0).getNumeroConta());
        assertEquals("222", extrato.get(1).getNumeroConta());
    }
}
