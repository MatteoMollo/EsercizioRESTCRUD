package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticoli;
import eu.winwinit.bcc.model.ArticoloResponse;
import eu.winwinit.bcc.model.OrdineResponse;
import eu.winwinit.bcc.repository.ArticoloRepository;
import eu.winwinit.bcc.repository.OrdineArticoliRepository;
import eu.winwinit.bcc.repository.OrdineRepository;

@Service("ordineService")
public class OrdineServiceImpl implements OrdineService{
	
	@Autowired
	private OrdineRepository ordineRepository;
	
	@Autowired
	private OrdineArticoliRepository ordineArticoliRepository;
	
	@Autowired
	private ArticoloRepository articoloRepository;
	
	//Richiesta Ordine by id leggo i dati della tabella ordine e ordinearticoli
	//e li trasformo per portarli ai model per la risposta
	@Override
	public OrdineResponse findOrdineById(Integer id) {
		Optional<Ordine> risposta = ordineRepository.findById(id);
		List<ArticoloResponse> listaArticoli = new ArrayList<ArticoloResponse>();
		for(OrdineArticoli articolo : ordineArticoliRepository.findAllByIdOrdine(id)) {
			ArticoloResponse articoloRisp = new ArticoloResponse(articolo.getArticolo().getId(),
																 articolo.getArticolo().getNome(),
																 articolo.getArticolo().getPrezzo(),
																 articolo.getQuantita());
			listaArticoli.add(articoloRisp);
		}
		OrdineResponse ordine = new OrdineResponse(risposta.get().getId(),
												   risposta.get().getIntestatario(),
												   risposta.get().getIndirizzo(),
												   listaArticoli);
		return ordine;
	}

	//Richiesta tutti gli ordini leggo i dati della tabella ordine e ordinearticoli
		//e li trasformo per portarli ai model per la risposta
	@Override
	public List<OrdineResponse> findAllOrdini() {
		List<Ordine> listaVecchia =  ordineRepository.findAll();
  		List<OrdineResponse> lista = new ArrayList<OrdineResponse>();
		for(Ordine ordine : listaVecchia) {
			List<ArticoloResponse> listaArtRisposta = new ArrayList<ArticoloResponse>();
			for(OrdineArticoli articolo : ordineArticoliRepository.findAllByIdOrdine(ordine.getId())) {
				ArticoloResponse articoloRisp = new ArticoloResponse(articolo.getArticolo().getId(),
																	 articolo.getArticolo().getNome(),
																	 articolo.getArticolo().getPrezzo(),
																	 articolo.getQuantita());
				listaArtRisposta.add(articoloRisp);
			}
			OrdineResponse oResponse = new OrdineResponse(ordine.getId(),
														  ordine.getIntestatario(),
														  ordine.getIndirizzo(),
														  listaArtRisposta);
			lista.add(oResponse);
		}
		return lista;
	}

	//Richiesta aggiunta oggetto ordine : prendo l'oggetto model che mi da l'utente 
	// e lo trasformo nelle due entity per poter salvare i dati nelle rispettive tabelle
	@Override
	public void saveOrdine(OrdineResponse richiesta) {
		 Ordine ordine = new Ordine(richiesta.getIntestatario(),
									richiesta.getIndirizzo());
		 ordine = ordineRepository.save(ordine);
		 for(ArticoloResponse articoloResponse : richiesta.getListaArticoli()) {
			 OrdineArticoli oa = new OrdineArticoli();
			 oa.setArticolo(articoloRepository.getOne(articoloResponse.getId()));
			 oa.setOrdine(ordine);
			 oa.setQuantita(articoloResponse.getQuantita());
			 ordineArticoliRepository.save(oa);
		 }
		
	}
	
	//elimino l'oggetto ordine dalla sua tabella e il suo riferimento alla tabella ordinearticoli
	public void delete(Integer id) {
		ordineArticoliRepository.deleteByIdOrdine(id);
		ordineRepository.deleteOrdineById(id);
	}
	
	//modifico il campo di un articolo contenuito in una lista articoli
	// del rispettivo ordine
	public void updateQuantitaArticoloByIdOrdine(Integer quantita, Integer idordine, Integer idarticolo) {
		ordineArticoliRepository.updateQuantitaArticoloByIdOrdine(quantita, idordine, idarticolo);
	}



}
