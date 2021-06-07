package entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class PedidoEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientesEntity cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedidoEntity> itens;

	public void save(PedidoEntity pedido) {
		// TODO Auto-generated method stub
		
	}

	public void setItens(List<ItemPedidoEntity> itemsPedido) {
		// TODO Auto-generated method stub
		
	}

	public void setCliente(ClientesEntity cliente2) {
		// TODO Auto-generated method stub
		
	}

	public void setDataPedido(LocalDate now) {
		// TODO Auto-generated method stub
		
	}

	public Optional<PedidoEntity> findByIdFetchItens(Integer id2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<PedidoEntity> findById(Integer id2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCliente(ClientesEntity cliente2) {
		// TODO Auto-generated method stub
		
	}

	public void setCliente(ClientesEntity cliente2) {
		// TODO Auto-generated method stub
		
	}

	public void setCliente(ClientesEntity cliente2) {
		// TODO Auto-generated method stub
		
	}
}
