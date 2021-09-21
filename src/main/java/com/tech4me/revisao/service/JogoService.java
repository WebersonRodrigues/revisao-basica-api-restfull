package com.tech4me.revisao.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.revisao.shared.JogoDTO;

import org.springframework.stereotype.Service;


public interface JogoService {
    
    List<JogoDTO> obterTodos();

    Optional<JogoDTO> obterPorId(String idJogo);

    JogoDTO adicionar(JogoDTO jogoDto);

    JogoDTO atualizar(String id, JogoDTO jogoDto);

    void delete(String idJogo);

}
