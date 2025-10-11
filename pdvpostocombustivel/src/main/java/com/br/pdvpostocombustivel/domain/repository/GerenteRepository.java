package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {

    // 🔹 Para autenticação
    Optional<Gerente> findByUsuarioAndSenha(String usuario, String senha);

    // 🔹 Para evitar duplicação de usuário
    boolean existsByUsuario(String usuario);
}
