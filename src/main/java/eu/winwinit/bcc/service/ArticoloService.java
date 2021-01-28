package eu.winwinit.bcc.service;

import java.util.List;

import eu.winwinit.bcc.entities.Articolo;

public interface ArticoloService {

	public List<Articolo> findAll();
	
	public Articolo findArticoloById(Integer id);
	
	public void save(Articolo articolo);
	
	public void delete(Integer id);
	
	public void updatePrezzoArticolo(Integer prezzo, String nome);
	
}