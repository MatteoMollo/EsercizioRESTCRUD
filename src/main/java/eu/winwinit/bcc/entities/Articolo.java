package eu.winwinit.bcc.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;


import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "articoli", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Articolo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Integer prezzo;


	@OneToMany(mappedBy="ordine")
	private List<OrdineArticoli> dettaglioOrdini;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false, length = 40)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "prezzo")
	public Integer getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

}