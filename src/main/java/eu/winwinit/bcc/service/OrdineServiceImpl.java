package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
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
			ArticoloResponse articoloRisp = new ArticoloResponse(
					articolo.getArticolo().getId(),
					articolo.getArticolo().getNome(),
					articolo.getArticolo().getPrezzo(),
					articolo.getQuantita()
					);
			listaArticoli.add(articoloRisp);
		}
		OrdineResponse ordine = new OrdineResponse(
				risposta.get().getId(),
				risposta.get().getIntestatario(),
				risposta.get().getIndirizzo(),
				listaArticoli
				);
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
				ArticoloResponse articoloRisp = new ArticoloResponse(
						articolo.getArticolo().getId(),
						articolo.getArticolo().getNome(),
						articolo.getArticolo().getPrezzo(),
						articolo.getQuantita()
						);
				listaArtRisposta.add(articoloRisp);
			}
			OrdineResponse oResponse = new OrdineResponse(
					ordine.getId(),
					ordine.getIntestatario(),
					ordine.getIndirizzo(),
					listaArtRisposta
					);
			lista.add(oResponse);
		}
		return lista;
	}

	//Richiesta aggiunta oggetto ordine : prendo l'oggetto model che mi da l'utente 
	// e lo trasformo nelle due entity per poter salvare i dati nelle rispettive tabelle
	@Override
	public String saveOrdine(OrdineResponse richiesta) {
		Ordine ordine = new Ordine(
				richiesta.getIntestatario(),
				richiesta.getIndirizzo()
				);
		String risposta = "Ordine creato correttamente con id: ";
		if(ordine.getIndirizzo().isBlank() || ordine.getIntestatario().isBlank()) {
			risposta = "Impossibile creare un ordine senza campi inseriti correttamente";
		}else {
			ordine = ordineRepository.save(ordine);
			risposta += ordine.getId();
			for(ArticoloResponse articoloResponse : richiesta.getListaArticoli()) {
				OrdineArticoli oa = new OrdineArticoli();
				oa.setArticolo(articoloRepository.getOne(articoloResponse.getId()));
				oa.setOrdine(ordine);
				oa.setQuantita(articoloResponse.getQuantita());
				if((oa.getQuantita() <= 0) || (oa.getArticolo().getPrezzo() <= 0)) {
					risposta = "Impossibile salvare un ordine i cui articoli presenti sono incompleti";
				}else {
					ordineArticoliRepository.save(oa);
				}
			}
		}
		return risposta;
	}

	//elimino l'oggetto ordine dalla sua tabella e il suo riferimento alla tabella ordinearticoli
	public String delete(Integer id) {
		String stringRisposta = "Ordine eliminato correttamente";
		Optional<Ordine> ordine = ordineRepository.findById(id);
		try {
			ordine.get();
			ordineArticoliRepository.deleteByIdOrdine(id);
			ordineRepository.deleteById(id);
		}catch(Exception e) {
			stringRisposta = "Impossibile eliminare un ordine non esistente";
		}
		return stringRisposta;
	}

	//modifico il campo di un articolo contenuito in una lista articoli
	// del rispettivo ordine
	public String updateQuantitaArticoloByIdOrdine(Integer quantita, Integer idordine, Integer idarticolo) {
		String stringRisposta = "Ordine modificato correttamente";
		Optional<Articolo> articolo = articoloRepository.findById(idarticolo);
		Optional<Ordine> ordine = ordineRepository.findById(idordine);
		if(quantita <= 0) {
			stringRisposta = "Impossibile impostare un valore negativo o uguale a 0 per la quantita' di un articolo";
		}else {
			try {
				ordine.get();
				articolo.get();
				ordineArticoliRepository.updateQuantitaArticoloByIdOrdine(quantita, idordine, idarticolo);
			}catch(Exception e) {
				stringRisposta = "Impossibile modificare un ordine non esistente";
			}
		}
		return stringRisposta;
	}
}