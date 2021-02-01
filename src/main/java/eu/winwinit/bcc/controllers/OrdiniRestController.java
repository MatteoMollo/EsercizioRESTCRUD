package eu.winwinit.bcc.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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
import eu.winwinit.bcc.model.OrdineResponse;
import eu.winwinit.bcc.security.JwtTokenProvider;
import eu.winwinit.bcc.service.OrdineService;

@RestController
@RequestMapping("/api/v1")
public class OrdiniRestController {
	
	@Autowired
	JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
	
	@Autowired
	OrdineService ordineService;
	
	
	 @RequestMapping(value = "/ordini-search", method = RequestMethod.GET)
	 @Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
	    public ResponseEntity<List<OrdineResponse>> ordiniSearch(
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		 List<OrdineResponse> ordineList = ordineService.findAllOrdini();
		 return new ResponseEntity<>(ordineList, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/ordini-search-by-id/{id}", method = RequestMethod.GET)
	 @Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
	    public ResponseEntity<Object> ordiniSearchById(@PathVariable( "id" ) Integer id,
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		 try {
			 OrdineResponse ordine = ordineService.findOrdineById(id);
			 return new ResponseEntity<>(ordine, HttpStatus.OK);
		 }catch(NoSuchElementException e) {
			 return new ResponseEntity<>("L'ordine cercato non esiste", HttpStatus.OK);
		 }
	    }
	 
	 
	 @RequestMapping(value = "/new-ordine", method = RequestMethod.POST)
	 @Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
	 public  ResponseEntity<String> ordiniInsert(
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken,
	 @RequestBody OrdineResponse ordine
	 ) {
		 ordineService.saveOrdine(ordine);
		 return new ResponseEntity<>(("Nuovo id creato = " + ordine.getIdOrdine()), HttpStatus.CREATED);
	 }
	 
	 
	 @RequestMapping(value = "/elimina-ord/{id}", method = RequestMethod.DELETE)
	 @Secured({AuthorityRolesConstants.ROLE_ADMIN})
	 public ResponseEntity<String> ordiniCanc(@PathVariable( "id" ) Integer id,
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken
	 ) {
		 String stringaRisposta = ordineService.delete(id);
		 return new ResponseEntity<>(stringaRisposta, HttpStatus.OK);
		 
	 }
	 
	 //DA FARE CONTROLLO SULL'ID ARTICOLO POSSIBILMENTE INESISTENTE
	 @RequestMapping(value = "/modifica-ord/{idordine}/{idarticolo}/{quantita}", method = RequestMethod.PUT)
	 @Secured({AuthorityRolesConstants.ROLE_ADMIN})
	 public ResponseEntity<String> ordiniMod(@PathVariable( "idordine" ) Integer idordine,
			 						@PathVariable( "idarticolo" ) Integer idarticolo,
			 						@PathVariable( "quantita" ) Integer quantita,
	 @RequestHeader(value=AuthorityRolesConstants.HEADER_STRING) String jwtToken
	 ) {
		 String stringaRisposta = ordineService.updateQuantitaArticoloByIdOrdine(quantita, idordine, idarticolo);
		 return new ResponseEntity<>(stringaRisposta, HttpStatus.OK);
		 
	 }

}
