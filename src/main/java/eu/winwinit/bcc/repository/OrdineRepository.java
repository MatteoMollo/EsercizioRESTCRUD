package eu.winwinit.bcc.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eu.winwinit.bcc.entities.Ordine;

@Repository("ordineRepository")
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
	
	public List<Ordine> findAll();
	
	@Transactional
	@Modifying
	@Query("delete from Ordine o where o.id = :id")
	public void deleteOrdineById(@Param("id")Integer id);
	
	public Optional<Ordine> findById(Integer id);
      
}