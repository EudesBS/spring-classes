package com.estudos.springboot.domain.repository;

import com.estudos.springboot.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto, Integer> {

}
