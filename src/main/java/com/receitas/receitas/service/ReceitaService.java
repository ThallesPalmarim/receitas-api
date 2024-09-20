package com.receitas.receitas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.receitas.receitas.model.Receita;

@Service
public class ReceitaService {
    private static List<Receita> receitas = new ArrayList<Receita>();

    public ReceitaService(){
        receitaFake();
    }
    public void receitaFake(){
        Receita receita = new Receita();
        receita.setId(1L);
        receita.setNome("Feijoada");
        receita.setIngredientes("Feijão e carne de porco");
        receitas.add(receita);
    }

    public List<Receita> findAll(){
        return receitas;
    }

    public Receita find(Receita receita){
        return receitas.stream().filter(r -> r.equals(receita)).findFirst().orElse(null);
    }

    public Receita find(Long id){
        return find(new Receita(id));
    }

    public void create (Receita receita){
        Long newId = (long) (receitas.size() +1);
        receita.setId(newId);
        receitas.add(receita);
    }
    public Boolean delete(Long id){
        Receita _receita = find(id);
        if(_receita != null){
            receitas.remove(_receita);
            return true;
        }
        return false;
    }
    public Boolean update(Receita receita){
        Receita _receita = find(receita);
        if(_receita !=null){
            _receita.setNome(receita.getNome());
            _receita.setIngredientes(receita.getIngredientes());
                return true;
        }
        return false;
    }
}
