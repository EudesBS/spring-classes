package com.estudos.springboot.service.impl;

import com.estudos.springboot.domain.repository.PedidoRepository;
import com.estudos.springboot.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository repository;

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }
}
