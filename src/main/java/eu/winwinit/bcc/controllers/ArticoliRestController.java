package eu.winwinit.bcc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.winwinit.bcc.constants.AuthorityRolesConstants;
import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.service.ArticoloService;

@RestController
@RequestMapping("/api/v1")
public class ArticoliRestController {

	@Autowired
	private ArticoloService articoloService;


	@RequestMapping(value = "/articoli-search", method = RequestMethod.GET)
	@Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
	public ResponseEntity<List<Articolo>> articoliSearch(
			@RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		List<Articolo> articoloList = new ArrayList<Articolo>();
		articoloList.addAll(articoloService.findAll());
		return new ResponseEntity<>(articoloList, HttpStatus.OK);
	}


	
	@RequestMapping(value = "/articoli-search-by-id/{id}", method = RequestMethod.GET)
	@Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
	public ResponseEntity<Object> articoloSearchById(
			@PathVariable( "id" ) Integer id,
			@RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken){
			Optional<Articolo> articolo = articoloService.findById(id);
		try {
			return new ResponseEntity<>(articolo.get(), HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>("Articolo non presente nel magazzino", HttpStatus.OK);
		}
		
	}


	@RequestMapping(value = "/new-articolo", method = RequestMethod.POST)
	@Secured({AuthorityRolesConstants.ROLE_ADMIN})
	public ResponseEntity<String> articoliInsert(
			@RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken,
			@RequestBody Articolo articolo
			) {
		articoloService.save(articolo);
		return new ResponseEntity<>(("nuovo id creato = " + articolo.getId()), HttpStatus.CREATED);
	}
	 

	@RequestMapping(value = "/elimina-art/{id}", method = RequestMethod.DELETE)
	@Secured({AuthorityRolesConstants.ROLE_ADMIN})
	public ResponseEntity<String> articoliCanc(@PathVariable( "id" ) Integer id,
			@RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken
			) {
		String stringaConferma = articoloService.delete(id);
		return new ResponseEntity<>(stringaConferma, HttpStatus.OK);

	}

	@RequestMapping(value = "/modifica-art/{id}/{prezzo}", method = RequestMethod.PUT)
	@Secured({AuthorityRolesConstants.ROLE_ADMIN})
	public ResponseEntity<String> articoliMod(@PathVariable( "id" ) Integer id, @PathVariable( "prezzo" ) Integer prezzo,
			@RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken
			) {
		String stringaRisposta = articoloService.updatePrezzoById(prezzo, id);
		return new ResponseEntity<>(stringaRisposta, HttpStatus.OK);

	}

}
