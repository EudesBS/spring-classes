package com.estudos.springboot.rest.dto;

import com.estudos.springboot.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/*
*
* {
    "cliente" : 1,
    "total" : 100,
    "items" : [
        {
            "produto" : 1,
            "quantidade" : 10
        }
    ]
}
*
* */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> items;


}
