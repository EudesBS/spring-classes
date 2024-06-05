package com.estudos.springboot.service.impl;

import com.estudos.springboot.domain.entity.Cliente;
import com.estudos.springboot.domain.entity.ItemPedido;
import com.estudos.springboot.domain.entity.Pedido;
import com.estudos.springboot.domain.entity.Produto;
import com.estudos.springboot.domain.enums.StatusPedido;
import com.estudos.springboot.domain.repository.ClienteRepository;
import com.estudos.springboot.domain.repository.ItemPedidoRepository;
import com.estudos.springboot.domain.repository.PedidoRepository;
import com.estudos.springboot.domain.repository.ProdutoRepository;
import com.estudos.springboot.exception.PedidoNaoEncontradoException;
import com.estudos.springboot.exception.RegraNegocioException;
import com.estudos.springboot.rest.dto.ItemPedidoDTO;
import com.estudos.springboot.rest.dto.PedidoDTO;
import com.estudos.springboot.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {


    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired
    private final PedidoRepository repository;

    @Autowired
    private final ItemPedidoRepository itemsPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
       Integer idCliente = dto.getCliente();
       Cliente cliente = clienteRepository
               .findById(idCliente)
               .orElseThrow(() -> new RegraNegocioException("Código de Cliente inválido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
          throw new RegraNegocioException("Não é possível executar um pedido sem items!");
        }

        return items.stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());


    }
}
