package com.estudos.springboot;

import com.estudos.springboot.domain.entity.Cliente;
import com.estudos.springboot.domain.entity.Pedido;
import com.estudos.springboot.domain.repository.ClienteRepository;
import com.estudos.springboot.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired ClienteRepository clientes,
                                   @Autowired PedidoRepository pedidos) {
        return args -> {
            System.out.println("\nSalvando Clientes\n");
            Cliente cliente = new Cliente();
            cliente.setNome("Ude");
            clientes.save(cliente);

            Cliente cliente2 = new Cliente("Julinha");
            clientes.save(cliente2);

            clientes.save(new Cliente("Outro cliente"));

            Pedido p = new Pedido();
            p.setCliente(cliente);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);
            pedidos.findByCliente(cliente).forEach(System.out::println);




//            Cliente test = clientes.findClienteFetchPedidos(cliente.getId());
//            System.out.println(test);
//            System.out.println(test.getPedidos());

//            List<Cliente> tem = clientes.encontrarPorNome("Ude");
//            tem.forEach(System.out::println);
//
//            boolean existe = clientes.existsByNome("Julinha");
//            System.out.println(existe);
//
//            List<Cliente> todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }
}
