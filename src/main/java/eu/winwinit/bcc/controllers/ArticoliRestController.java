package eu.winwinit.bcc.controllers;

import java.util.ArrayList;
import java.util.List;

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
import eu.winwinit.bcc.security.JwtTokenProvider;
import eu.winwinit.bcc.service.ArticoloService;

@RestController
@RequestMapping("/api/v1")
public class ArticoliRestController {

	@Autowired
	JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
	
	@Autowired
	ArticoloService articoloService;
	
	
	 @RequestMapping(value = "/articoli-search", method = RequestMethod.GET)
	 @Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
	    public ResponseEntity<List<Articolo>> articoliSearch(
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		 List<Articolo> articoloList = new ArrayList<Articolo>();
		 articoloList.addAll(articoloService.findAll());
		 return new ResponseEntity<>(articoloList, HttpStatus.OK);
	    }
	 
	 
	 @RequestMapping(value = "/articoli-search-by-id/id={id}", method = RequestMethod.GET)
	 @Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
	    public ResponseEntity<Articolo> articoloSearchById(
    		@PathVariable( "id" ) Integer id,
    		@RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		 Articolo articolo = articoloService.findArticoloById(id);
		 return new ResponseEntity<>(articolo, HttpStatus.OK);
	    }
	 
	 
	 @RequestMapping(value = "/new-articolo", method = RequestMethod.POST)
	 @Secured({AuthorityRolesConstants.ROLE_ADMIN})
	 public HttpStatus articoliInsert(
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken,
	 @RequestBody Articolo articolo
	 ) {
		 articoloService.save(articolo);
		 return HttpStatus.CREATED;
	 }
	 
	 @RequestMapping(value = "/elimina-art/id={id}", method = RequestMethod.DELETE)
	 @Secured({AuthorityRolesConstants.ROLE_ADMIN})
	 public HttpStatus articoliCanc(@PathVariable( "id" ) Integer id,
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken
	 ) {
		 articoloService.delete(id);
		 return HttpStatus.OK;
		 
	 }
	 
	 @RequestMapping(value = "/modifica-art/{nome}/prezzo={prezzo}", method = RequestMethod.PUT)
	 @Secured({AuthorityRolesConstants.ROLE_ADMIN})
	 public HttpStatus articoliMod(@PathVariable( "nome" ) String nome, @PathVariable( "prezzo" ) Integer prezzo,
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken
	 ) {
		 articoloService.updatePrezzoArticolo(prezzo, nome);;
		 return HttpStatus.OK;
		 
	 }
		 
}
