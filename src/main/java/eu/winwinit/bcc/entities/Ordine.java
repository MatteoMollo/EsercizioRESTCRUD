package eu.winwinit.bcc.entities;


import static javax.persistence.GenerationType.IDENTITY;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "ordini", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Ordine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String intestatario;
	private String indirizzo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "articolo") 
	private List<OrdineArticoli> listaArticoli;
	
	public Ordine() {
	}

   	public Ordine(String intestatario, String indirizzo, List<OrdineArticoli> listaArticoli) {
		this.intestatario = intestatario;
		this.indirizzo = indirizzo;
		this.listaArticoli = listaArticoli;
	}
   	
   	public Ordine(Integer id, String intestatario, String indirizzo) {
   		this.id = id;
		this.intestatario = intestatario;
		this.indirizzo = indirizzo;
	}
   	
   	public Ordine(String intestatario, String indirizzo) {
		this.intestatario = intestatario;
		this.indirizzo = indirizzo;
	}


	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "intestatario", nullable = false, length = 40)
	public String getIntestatario() {
		return this.intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	@Column(name = "indirizzo")
	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<OrdineArticoli> getListaArticoli() {
		return listaArticoli;
	}

	public void setListaArticoli(List<OrdineArticoli> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}
		

}
