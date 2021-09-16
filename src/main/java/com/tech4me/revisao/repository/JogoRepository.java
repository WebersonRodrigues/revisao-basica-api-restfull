package com.tech4me.revisao.repository;

import com.tech4me.revisao.model.Jogo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends MongoRepository<Jogo, String> {
    
}
