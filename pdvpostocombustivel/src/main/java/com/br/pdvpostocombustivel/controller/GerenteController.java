package com.br.pdvpostocombustivel.controller;

import com.br.pdvpostocombustivel.domain.entity.Gerente;
import com.br.pdvpostocombustivel.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/gerentes")
@CrossOrigin(origins = "*")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @PostMapping
    public ResponseEntity<Gerente> criarGerente(@RequestBody Gerente gerente) {
        try {
            return ResponseEntity.ok(gerenteService.salvarGerente(gerente));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Gerente>> listarGerentes() {
        return ResponseEntity.ok(gerenteService.listarGerentes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> buscarGerente(@PathVariable Long id) {
        return gerenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> atualizarGerente(@PathVariable Long id, @RequestBody Gerente gerente) {
        try {
            return ResponseEntity.ok(gerenteService.atualizarGerente(id, gerente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGerente(@PathVariable Long id) {
        try {
            gerenteService.deletarGerente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔐 Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        String usuario = credenciais.get("usuario");
        String senha = credenciais.get("senha");

        Optional<Gerente> gerente = gerenteService.autenticar(usuario, senha);

        if (gerente.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "mensagem", "Login bem-sucedido!",
                    "gerente", gerente.get()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of("erro", "Usuário ou senha inválidos"));
        }
    }
}