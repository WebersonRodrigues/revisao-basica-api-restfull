package com.tech4me.revisao.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tech4me.revisao.model.Jogo;
import com.tech4me.revisao.repository.JogoRepository;
import com.tech4me.revisao.shared.JogoDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class JogoServiceImpl implements JogoService {

    @Autowired
    JogoRepository repositorioJogo;

    @Override
    public List<JogoDTO> obterTodos() {
        // Pedindo o repositorio para ir ao banco e me retorar todos os jogos.
        List<Jogo> jogos = repositorioJogo.findAll();

        // Criando o nosso objeto para converter informações.
        ModelMapper mapper = new ModelMapper();

        // Aqui eu varro a lista de jogo e para cada jogo eu converto o mesmo
        // em JogoDTO e ao final retorno esta lista.
        return jogos.stream()
        .map(jogo -> mapper.map(jogo, JogoDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public JogoDTO adicionar(JogoDTO jogoDto) {
   
        // Criando o nosso objeto para converter informações.
        ModelMapper mapper = new ModelMapper();

        // Convertendo o jogoDto em um Jogo model.
        Jogo jogo = mapper.map(jogoDto, Jogo.class);

        // Removendo o id para forçar o cadastro.
        jogo.setId(null);

        // Salvando o jogo no banco, ou seja cadastrando.
        jogo = repositorioJogo.save(jogo);

        // Convertendo o jogo model em um jogoDto para devolver.
        return mapper.map(jogo, JogoDTO.class);
    }

    @Override
    public Optional<JogoDTO> obterPorId(String idJogo) {
        
        // Tento encontrar um jogo pelo seu id.
        // Se encontrar ele me devolve um optional com o jogo dentro
        // Se não me devolve um optional vazio.
        Optional<Jogo> optionalJogo = repositorioJogo.findById(idJogo);

        // Se não encontrar lanço uma exception.
        if(optionalJogo.isEmpty()){
            throw new InputMismatchException("Não foi encontrado o jogo com o id: " + idJogo);
        }

        // Tirando o jogo model de dentro do optional e convertendo ele para um jogoDTO.
        JogoDTO  jogoDto = new ModelMapper().map(optionalJogo.get(), JogoDTO.class);

       // Crio um otional de jogoDto para devolver.
        return Optional.of(jogoDto);
    }

    @Override
    public void delete(String idJogo) {
        // Aqui poderia criar uma logica para saber se o jogo existe com esse id.
        repositorioJogo.deleteById(idJogo);
    }
    
}
