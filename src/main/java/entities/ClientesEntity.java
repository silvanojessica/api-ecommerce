package entities;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class ClientesEntity {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ID")
	    private Integer id;
	 	
	 	@Column(name = "e-mail")
	 	private String email;
	 	
	 	@Column(name="username")
	    @NotEmpty(message = "{campo.nome.obrigatorio}")
	    private String username;
	 	
	    @Column(name = "nome", length = 100)
	    @NotEmpty(message = "{campo.nome.obrigatorio}")
	    private String nome;

	    @Column(name = "CPF", length = 11)
	    @NotEmpty(message = "{campo.cpf.obrigatorio}")
	    private String cpf;

	    @Column(name="telefone", length = 11)
	    @NotEmpty(message = "{campo.telefone.obrigatorio}")
	    private String telefone;
	    
	    @Column (name="nascimento")
	    //tentar validacao usuario > 18
	    private LocalDate datanascimento;
	    
	    //tem que entrar o endereco_id
	    
	    
	    @JsonIgnore
	    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
	    private Set<PedidoEntity> pedidos;

	   public void Cliente(Integer id, String nome) {
	        this.id = id;
	        this.nome = nome;
	    }

	public Object findById(Integer idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<PedidoEntity> findById(Integer idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	}

