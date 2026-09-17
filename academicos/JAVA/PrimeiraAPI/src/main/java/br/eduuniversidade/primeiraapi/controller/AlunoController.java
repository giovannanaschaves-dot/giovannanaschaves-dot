package br.eduuniversidade.primeiraapi.controller;

import br.eduuniversidade.primeiraapi.model.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos") //define a rota base
public class AlunoController {
    // simulação de tabela (banco)
    private List<Aluno> alunos = new ArrayList<>();
    // simulação do contador auto-incremental do BD
    private long proximoId = 1;

    // metodo GET HTTP que retorna a lista
    @GetMapping
    public List<Aluno> listarTodos() {return alunos;}

    // metodo POST HTTP qe cria um aluno e adiciona na lista
    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno novoAluno) {
        // atribui o Id simulado e incrementa para o próximo
        novoAluno.setId(proximoId++);
        // Adiciona na lista
        alunos.add(novoAluno);
        // Retorna status 201 (Created) e o objeto criado com novo Id
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }
}

// {
//    "nome":"Caua",
//    "matricula":"22550997",
//    "curso":"Ciência da Computação"
//
//}