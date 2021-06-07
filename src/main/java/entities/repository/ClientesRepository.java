package entities.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.ClientesEntity;

public interface ClientesRepository extends JpaRepository<ClientesEntity, Integer> {
	 
		@Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
	    List<ClientesEntity> encontrarPorNome( @Param("nome") String nome );

	    @Query(" delete from Cliente c where c.nome =:nome ")
	    @Modifying
	    void deleteByNome(String nome);

	    boolean existsByNome(String nome);

	    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
	    ClientesEntity findClienteFetchPedidos( @Param("id") Integer id );


	}

