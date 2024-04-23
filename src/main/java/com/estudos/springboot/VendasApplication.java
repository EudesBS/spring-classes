package com.estudos.springboot;

import com.estudos.springboot.domain.entity.Cliente;
import com.estudos.springboot.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired ClienteRepository clientes) {
        return args -> {
            System.out.println("\nSalvando Clientes\n");
            Cliente cliente = new Cliente();
            cliente.setNome("Ude");
            clientes.salvar(cliente);

            Cliente cliente2 = new Cliente("Julinha");
            clientes.salvar(cliente2);

            clientes.salvar(new Cliente("Outro cliente"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("\nAtualizando Clientes\n");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("\nBuscando Clientes\n");
            clientes.buscarPorNome("Outro").forEach(System.out::println);

            System.out.println("\nDeletando Clientes\n");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            todosClientes = clientes.obterTodos();
            if (todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado");
            } else {
                todosClientes.forEach(System.out::println);
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }
}
