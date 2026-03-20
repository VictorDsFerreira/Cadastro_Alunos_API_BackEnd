package com.api_alunos.api_Alunos.controller;

import com.api_alunos.api_Alunos.model.Aluno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private List<Aluno> aluno = new ArrayList<>();

    public AlunoController() {
        aluno.add(new Aluno(1, "Junior", "junior@hotmail.com"));
        aluno.add(new Aluno(2, "Larissa", "larissa.lala@hotmail.com"));
        aluno.add(new Aluno(3, "Hugo", "hugo@gmail.com"));
    }

    @GetMapping
    public List<Aluno> listar(){
        return aluno;
    }

    @GetMapping("/{ra}")
    public Aluno porRA(@PathVariable int ra){
        for(Aluno a : aluno){
            if(a.getRa() == ra){
                return a;
            }
        }
        return null;
    }

    @PostMapping
    public String adicionarAluno(@RequestBody Aluno novoAluno) {
        if (novoAluno.getNome() == null || novoAluno.getNome().isEmpty()) {
            return "Erro: O nome do aluno não pode ser vazio.";
        }
        if (novoAluno.getEmail() == null || !novoAluno.getEmail().contains("@")) {
            return "Erro: O email deve ser válido (conter @).";
        }
        for (Aluno a : aluno) {
            if (a.getRa() == novoAluno.getRa()) {
                return "Erro: Já existe um aluno com este RA.";
            }
        }
        aluno.add(novoAluno);
        return "Aluno adicionado com sucesso!";
    }

    @DeleteMapping("/{ra}")
    public String removerAluno(@PathVariable int ra){
        for(Aluno a : aluno){
            if(a.getRa() == ra){
                aluno.remove(a);
                return "Aluno excluido com sucesso!";
            }
        }
        return "Aluno não encontrado";
    }

    @PutMapping("/{ra}")
    public Object atualizarAluno(@PathVariable int ra, @RequestBody Aluno dadosAtualizados) {
        if (dadosAtualizados.getNome() == null || dadosAtualizados.getNome().isEmpty()) {
            return "Erro: O nome não pode ser vazio.";
        }
        if (dadosAtualizados.getEmail() == null || !dadosAtualizados.getEmail().contains("@")) {
            return "Erro: Email inválido.";
        }
        for (Aluno a : aluno) {
            if (a.getRa() == ra) {
                a.setNome(dadosAtualizados.getNome());
                a.setEmail(dadosAtualizados.getEmail());
                return a;
            }
        }
        return "Aluno não encontrado.";
    }
}
