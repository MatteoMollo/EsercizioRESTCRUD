package eu.winwinit.bcc.service;

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
	public String save(Articolo articolo) {
		String risposta = "Articolo creato correttamente con id: ";
		if(articolo.getNome() == null || articolo.getPrezzo() == null || articolo.getPrezzo() <= 0) {
			risposta = "Impossibile creare un articolo i cui campi non sono specificati o non accettabili";
		}else {
			articoloRepository.save(articolo);
		}
		return risposta;
	}

	//ritorna stringa con controllo che l'articolo da eliminare non sia presente in un ordine
	//e che esista effettivamente
	public String delete(Integer id) {
		String stringaConferma = "Articolo eliminato correttamente";
		List<OrdineArticoli> ordineArticoli = ordineArticoliRepository.findByIdArticolo(id);
		if(ordineArticoli.size() != 0) {
			stringaConferma = "Impossibile eliminare un articolo presente in un ordine";
		}else {
			Optional<Articolo> articolo = articoloRepository.findById(id);
			try {
				articolo.get();
				articoloRepository.deleteById(id);
			}catch(Exception e) {
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
		String stringaRisposta = "Articolo modificato correttamente";
		if(prezzo <= 0) {
			stringaRisposta = "Impossibile stabilire un prezzo minore o uguale a 0";
		}else {
			Optional<Articolo> articolo = articoloRepository.findById(id);
			try {
				articolo.get();
				articoloRepository.updatePrezzoById(prezzo, id);
			}catch(Exception e) {
				stringaRisposta = "Impossibile modificare un articolo non esistente";
			}
		}
		return stringaRisposta;
	}
}