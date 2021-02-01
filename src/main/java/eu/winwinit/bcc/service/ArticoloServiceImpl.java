package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.entities.OrdineArticoli;
import eu.winwinit.bcc.repository.ArticoloRepository;
import eu.winwinit.bcc.repository.OrdineArticoliRepository;

@Service("articoloService")
public class ArticoloServiceImpl implements ArticoloService{

	@Autowired
	private ArticoloRepository articoloRepository;

	@Autowired
	private OrdineArticoliRepository ordineArticoliRepository;

	@Override
	public List<Articolo> findAll() {
		return articoloRepository.findAll();
	}

	@Override
	public void save(Articolo articolo) {
		articoloRepository.save(articolo);

	}

	//ritorna stringa
	public String delete(Integer id) {
		List<OrdineArticoli> listaOrdiniArticoli = ordineArticoliRepository.findAll();
		List<Articolo> listaArticoli = articoloRepository.findAll();
		List<Integer> idArticoli = new ArrayList<Integer>();
		List<Integer> idArticolitotali = new ArrayList<Integer>();
		String stringaConferma = "Articolo eliminato correttamente";
		for(OrdineArticoli referenze : listaOrdiniArticoli) {
			idArticoli.add(referenze.getArticolo().getId());
		}
		for(Articolo articolo : listaArticoli) {
			idArticolitotali.add(articolo.getId());
		}
		if(idArticoli.contains(id)) {
			stringaConferma = "Impossibile eliminare un articolo presente in un ordine";
		}else {
			if(idArticolitotali.contains(id)) {
				articoloRepository.deleteById(id);
			}else {
				stringaConferma = "Impossibile eliminare un articolo non esistente";
			}

		}
		return stringaConferma;
	}

	@Override
	public Optional<Articolo> findById(Integer id) {
		return articoloRepository.findById(id);
	}

	@Override
	public String updatePrezzoById(Integer prezzo, Integer id) {
		// TODO Auto-generated method stub
		List<Articolo> listaArticoli = articoloRepository.findAll();
		List<Integer> idArticoli = new ArrayList<Integer>();
		String stringaRisposta = "Articolo modificato correttamente";
		for(Articolo articolo : listaArticoli) {
			idArticoli.add(articolo.getId());
		}
		if(idArticoli.contains(id)) {
			articoloRepository.updatePrezzoById(prezzo, id);
		}else {
			stringaRisposta = "Impossibile modificare un articolo non esistente";
		}
		return stringaRisposta;
	}

}
