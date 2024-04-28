package com.estudos.springboot.rest.controller;

import com.estudos.springboot.domain.entity.Cliente;
import com.estudos.springboot.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    /*
    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente){
        return String.format("Hello %s", nomeCliente);
    }*/

    /*
    @GetMapping(value = "/outro/{nid}")
    @ResponseBody
    public ResponseEntity<Cliente> getClientId(@PathVariable("nid") Integer id){
        Cliente c = clienteRepository.findById(id).get();

        return ResponseEntity.ok(c);
    }*/

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity getClienteById (@PathVariable Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        Cliente saveCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(saveCliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> deleteCliente = clienteRepository.findById(id);

        if (deleteCliente.isPresent()){
            clienteRepository.delete(deleteCliente.get());
            return  ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente){
        //Optional<Cliente> updateCliente = clienteRepository.
        return clienteRepository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity find (Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clienteRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }

}
