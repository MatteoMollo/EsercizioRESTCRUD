package eu.winwinit.bcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eu.winwinit.bcc.entities.Articolo;

@Repository("articoloRepository")
public interface ArticoloRepository extends JpaRepository<Articolo, Integer> {

//	
//	
//	@Query("select a from Articolo a where id = :id")
//	Articolo findArticoloById(@Param("id")Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query("delete from Articolo where id = :id")
//	void deleteArticolo(@Param("id")Integer id);
//	
	@Transactional
	@Modifying
	@Query("update Articolo set prezzo = :prezzo where id = :id")
	void updatePrezzoById(@Param("prezzo")Integer prezzo,
							@Param("id")Integer id);
	
}