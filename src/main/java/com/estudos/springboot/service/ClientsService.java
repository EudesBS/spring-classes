package com.estudos.springboot.service;

import com.estudos.springboot.model.Cliente;
import com.estudos.springboot.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private ClientsRepository repository;

    @Autowired
    public void ClientsService(ClientsRepository repository){
        this.repository = repository;
    }


    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){

    }
}
