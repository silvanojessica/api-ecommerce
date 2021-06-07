package service;

import java.util.Optional;

import dto.PedidoDTO;
import entities.StatusPedido;

public interface Pedido {
	Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
