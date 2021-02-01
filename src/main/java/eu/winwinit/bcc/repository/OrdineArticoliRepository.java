package eu.winwinit.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eu.winwinit.bcc.entities.OrdineArticoli;

@Repository("ordineArticoliRepository")
public interface OrdineArticoliRepository extends JpaRepository<OrdineArticoli, Integer>{

	@Query("select oa from OrdineArticoli oa where idordine=:idOrdine")
	public List<OrdineArticoli> findAllByIdOrdine(@Param("idOrdine")Integer idOrdine);

	@Transactional
	@Modifying
	@Query("delete from OrdineArticoli where idordine = :idOrdine")
	public void deleteByIdOrdine(@Param("idOrdine")Integer idOrdine);

	@Transactional
	@Modifying
	@Query("update OrdineArticoli set quantita = :quantita where idordine = :idordine and idarticolo = :idarticolo")
	public void updateQuantitaArticoloByIdOrdine(
			@Param("quantita")Integer quantita, 
			@Param("idordine")Integer idordine,
			@Param("idarticolo")Integer idarticolo
			);
	
	@Query("select oa from OrdineArticoli oa where idarticolo = :idarticolo")
	public List<OrdineArticoli> findByIdArticolo(@Param("idarticolo")Integer idarticolo);
}