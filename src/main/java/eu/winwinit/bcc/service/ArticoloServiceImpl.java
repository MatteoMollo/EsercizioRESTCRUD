package eu.winwinit.bcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.repository.ArticoloRepository;

@Service("articoloService")
public class ArticoloServiceImpl implements ArticoloService{

	@Autowired
	private ArticoloRepository articoloRepository;
	
	@Override
	public List<Articolo> findAll() {
		return articoloRepository.findAll();
	}

	@Override
	public void save(Articolo articolo) {
		articoloRepository.save(articolo);
		
	}
	
	public void delete(Integer id) {
		articoloRepository.deleteArticolo(id);
	}

	@Override
	public Articolo findArticoloById(Integer id) {
		// TODO Auto-generated method stub
		return articoloRepository.findArticoloById(id);
	}

	@Override
	public void updatePrezzoArticolo(Integer prezzo, String nome) {
		// TODO Auto-generated method stub
		articoloRepository.updatePrezzoArticolo(prezzo, nome);
	}
	
}
