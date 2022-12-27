package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
    
}
