package dto;

import java.math.BigDecimal;
import java.util.List;

public class InformacoesPedidoDTO {
	private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private List<InformacaoItemPedidoDTO> items;
}
