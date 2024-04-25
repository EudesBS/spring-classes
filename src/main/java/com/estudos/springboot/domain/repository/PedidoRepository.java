package com.estudos.springboot.domain.repository;

import com.estudos.springboot.domain.entity.Cliente;
import com.estudos.springboot.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);

}
