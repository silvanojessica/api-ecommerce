package entities;


	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	
	@Entity
	@Table(name = "usuario")
	public class UsuarioEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    @Column
	    @NotEmpty(message = "{campo.login.obrigatorio}")
	    private String login;
	    @Column
	    @NotEmpty(message = "{campo.senha.obrigatorio}")
	    private String senha;
	    @Column
	    private boolean admin;
		public boolean isAdmin() {
			// TODO Auto-generated method stub
			return false;
		}
		public Object getSenha() {
			// TODO Auto-generated method stub
			return null;
		}
		public Object getLogin() {
			// TODO Auto-generated method stub
			return null;
		}

}
