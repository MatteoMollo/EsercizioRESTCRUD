package eu.winwinit.bcc.model;

////Modello utilizzato al solo fine di stampare la richiesta del client in una GET
//e per prendere i dati in una POST

public class ArticoloResponse {

	private int id;
	private String nome;
	private int prezzo;
	private int quantita;
	
	public ArticoloResponse(int id, String nome, int prezzo, int quantita) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.quantita = quantita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
	
}
