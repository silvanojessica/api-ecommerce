package entities;


	import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	
	@Entity
	@Table(name = "produto")
	public class ProdutoEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "descricao")
	    @NotEmpty(message = "{campo.descricao.obrigatorio}")
	    private String descricao;

	    @Column(name = "preco_unitario")
	    @NotEmpty(message = "{campo.preco.obrigatorio}")
	    private BigDecimal preco;
}
