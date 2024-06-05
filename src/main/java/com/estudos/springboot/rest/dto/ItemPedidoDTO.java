package com.estudos.springboot.rest.dto;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPedidoDTO {

    private Integer produto;
    private Integer quantidade;
}
