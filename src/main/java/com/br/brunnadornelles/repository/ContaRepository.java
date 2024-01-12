package com.br.brunnadornelles.repository;

import com.br.brunnadornelles.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ContaRepository extends JpaRepository<Conta,Long> {
    Conta findByNumeroConta (String numeroConta);
}
