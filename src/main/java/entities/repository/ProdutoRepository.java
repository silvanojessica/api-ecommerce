package entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.ProdutoEntity;


public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer>{

}
