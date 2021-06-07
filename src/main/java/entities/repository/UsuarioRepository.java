package entities.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	Optional<UsuarioEntity> findByLogin(String login);
}
