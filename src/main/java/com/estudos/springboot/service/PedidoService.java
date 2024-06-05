package com.estudos.springboot.service;

import com.estudos.springboot.domain.entity.Pedido;
import com.estudos.springboot.domain.enums.StatusPedido;
import com.estudos.springboot.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer Id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
