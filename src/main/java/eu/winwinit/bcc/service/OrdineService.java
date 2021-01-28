package eu.winwinit.bcc.service;

import java.util.List;

import eu.winwinit.bcc.model.OrdineResponse;

public interface OrdineService {

	public List<OrdineResponse> findAllOrdini();
	
	public void saveOrdine(OrdineResponse richiesta);
	
	public void delete(Integer id);
	
	public void updateQuantitaArticoloByIdOrdine(Integer quantita, Integer idordine, Integer idarticolo);
	
	public OrdineResponse findOrdineById(Integer id);
}
