package com.br.pdvpostocombustivel.controller;

import com.br.pdvpostocombustivel.domain.entity.Frentista;
import com.br.pdvpostocombustivel.service.FrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frentistas")
@CrossOrigin(origins = "*")
public class FrentistaController {

    @Autowired
    private FrentistaService frentistaService;

    @PostMapping
    public ResponseEntity<Frentista> criarFrentista(@RequestBody Frentista frentista) {
        return ResponseEntity.ok(frentistaService.salvarFrentista(frentista));
    }

    @GetMapping
    public ResponseEntity<List<Frentista>> listarFrentistas() {
        return ResponseEntity.ok(frentistaService.listarFrentistas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frentista> buscarFrentista(@PathVariable Long id) {
        return frentistaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Frentista> atualizarFrentista(@PathVariable Long id, @RequestBody Frentista frentista) {
        try {
            Frentista atualizado = frentistaService.atualizarFrentista(id, frentista);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFrentista(@PathVariable Long id) {
        try {
            frentistaService.deletarFrentista(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
