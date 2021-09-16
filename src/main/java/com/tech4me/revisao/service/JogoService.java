package com.tech4me.revisao.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.revisao.shared.JogoDTO;

import org.springframework.stereotype.Service;

@Service
public interface JogoService {
    
    List<JogoDTO> obterTodos();

    Optional<JogoDTO> obterPorId(String idJogo);

    JogoDTO adicionar(JogoDTO jogoDto);

    void delete(String idJogo);

}
