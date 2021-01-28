package eu.winwinit.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eu.winwinit.bcc.entities.Articolo;

@Repository("articoloRepository")
public interface ArticoloRepository extends JpaRepository<Articolo, Integer> {
	
	List<Articolo> findAll();
	
	
	@Query("select a from Articolo a where id = :id")
	Articolo findArticoloById(@Param("id")Integer id);
	
	@Transactional
	@Modifying
	@Query("delete from Articolo where id = :id")
	void deleteArticolo(@Param("id")Integer id);
	
	@Transactional
	@Modifying
	@Query("update Articolo set prezzo = :prezzo where nome = :nome")
	void updatePrezzoArticolo(@Param("prezzo")Integer prezzo,
							  @Param("nome")String nome);
	
}