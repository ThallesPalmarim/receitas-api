package com.receitas.receitas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.receitas.receitas.model.Receita;
import com.receitas.receitas.service.ReceitaService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;
    
    @GetMapping
    public ResponseEntity<List<Receita>> getAll() {
        return ResponseEntity.ok(receitaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> get(@PathVariable("id") Long id) {
        Receita receita = receitaService.find(id);

        if(receita != null){
            return ResponseEntity.ok(receita);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Receita> create(@RequestBody Receita receita){
        receitaService.create(receita);
        URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}").buildAndExpand(receita.getId())
                                    .toUri();
        return ResponseEntity.created(location).body(receita);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Receita> delete(@PathVariable("id") Long id){
        if(receitaService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping
    public ResponseEntity<Receita> update(@RequestBody Receita receita) {        
        if(receitaService.update(receita)){
            return ResponseEntity.ok(receita);
        }
        return ResponseEntity.notFound().build();
    }
}
