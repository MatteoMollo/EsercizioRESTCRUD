package eu.winwinit.bcc.service;

import java.util.List;
import java.util.Optional;

import eu.winwinit.bcc.entities.Articolo;

public interface ArticoloService {

	public List<Articolo> findAll();
	
	public Optional<Articolo> findById(Integer id);
	
	public String save(Articolo articolo);
	
	public String delete(Integer id);
	
	public String updatePrezzoById(Integer prezzo, Integer id);
	
}