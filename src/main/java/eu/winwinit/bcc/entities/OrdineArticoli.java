package eu.winwinit.bcc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ordini_articoli", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class OrdineArticoli {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="idordine", nullable=false)
	private Ordine ordine;
	
	@ManyToOne
	@JoinColumn(name="idarticolo", nullable=false)
	private Articolo articolo;
	
	private Integer quantita;
	
	public OrdineArticoli() {
		
	}



	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	@Column(name = "quantita")
	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	
	
	
	

}
