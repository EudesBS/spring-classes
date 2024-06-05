package com.estudos.springboot.exception;

public class PedidoNaoEncontradoException extends RuntimeException {


    public PedidoNaoEncontradoException() {
        super("Pedido não econtrado!");
    }
}
