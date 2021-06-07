package service;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.UsuarioEntity;
import entities.repository.UsuarioRepository;

@Service
	public class UsuarioServiceImpl implements UserDetailsService {

	    @Autowired
	    private PasswordEncoder encoder;

	    @Autowired
	    private UsuarioRepository repository;

	    @Transactional
	    public UsuarioEntity salvar(UsuarioEntity usuario){
	        return repository.save(usuario);
	    }

	    public UserDetails autenticar( UsuarioEntity usuario ){
	        UserDetails user = loadUserByUsername(usuario.getLogin());
	        boolean senhasBatem = encoder.matches( usuario.getSenha(), user.getPassword() );

	        if(senhasBatem){
	            return user;
	        }

	        throw new SenhaInvalidaException();
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        UsuarioEntity usuario = repository.findByLogin(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

	        String[] roles = usuario.isAdmin() ?
	                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

	        return User
	                .builder()
	                .username(usuario.getLogin())
	                .password(usuario.getSenha())
	                .roles(roles)
	                .build();
	    }
}
