package com.tech4me.revisao.controller;

import java.util.List;
import java.util.Optional;

import com.tech4me.revisao.service.JogoService;
import com.tech4me.revisao.shared.JogoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {
    
    @Autowired
    JogoService servicoJogo;

    @GetMapping
    public ResponseEntity<List<JogoDTO>> obterTodos(){
        // Aqui pego do meu serviço a lista de jogadores
        List<JogoDTO> jogadores = servicoJogo.obterTodos();

        // Retorno uma resposta personalizada com a lista de jogadores e o statuscode ok.
        return new ResponseEntity<>(jogadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<JogoDTO>> obterPorId(@PathVariable String id){
        Optional<JogoDTO> jogo = servicoJogo.obterPorId(id);

        return new ResponseEntity<>(jogo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JogoDTO> adicionar(@RequestBody JogoDTO jogoDto ){
        // Aqui mando o serviço adicionar o novo jogo
        jogoDto = servicoJogo.adicionar(jogoDto);

        // Aqui devolvo a resposta para o usuario.
        return new ResponseEntity<>(jogoDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        servicoJogo.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
