package eu.winwinit.bcc.model;

import java.util.List;

//Modello utilizzato al solo fine di stampare la richiesta del client in una GET
//e per prendere i dati in una POST

public class OrdineResponse {
	
	private int idOrdine;
	private String intestatario;
	private String indirizzo;
	private List<ArticoloResponse> listaArticoli;
	
	public OrdineResponse(int idOrdine, String intestatario, String indirizzo, List<ArticoloResponse> listaArticoli) {
		super();
		this.idOrdine = idOrdine;
		this.intestatario = intestatario;
		this.indirizzo = indirizzo;
		this.listaArticoli = listaArticoli;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<ArticoloResponse> getListaArticoli() {
		return listaArticoli;
	}

	public void setListaArticoli(List<ArticoloResponse> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}
}