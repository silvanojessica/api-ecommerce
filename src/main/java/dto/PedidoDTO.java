package dto;

import java.math.BigDecimal;
import java.util.List;

import com.sun.istack.NotNull;

import entities.NotEmpty;

public class PedidoDTO {
	@NotEmpty(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;

	@NotEmpty(message = "{campo.total-pedido.obrigatorio}")
	private BigDecimal total;

	@NotEmpty(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> items;

	public Integer getCliente() {
		// TODO Auto-generated method stub
		return null;
	}

}
