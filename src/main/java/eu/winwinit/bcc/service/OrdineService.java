package eu.winwinit.bcc.service;

import java.util.List;

import eu.winwinit.bcc.model.OrdineResponse;

public interface OrdineService {

	public List<OrdineResponse> findAllOrdini();
	
	public String saveOrdine(OrdineResponse richiesta);
	
	public String delete(Integer id);
	
	public String updateQuantitaArticoloByIdOrdine(Integer quantita, Integer idordine, Integer idarticolo);
	
	public OrdineResponse findOrdineById(Integer id);
}