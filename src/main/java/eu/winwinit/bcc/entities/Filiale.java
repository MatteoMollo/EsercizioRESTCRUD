package eu.winwinit.bcc.entities;
// Generated 2-ott-2019 9.39.22 by Hibernate Tools 4.3.5.Final


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "filiali")
public class Filiale implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Integer codice;
	private String cab;
	private Date lastModify;

	public Filiale() {
	}

	public Filiale(String nome, String cab, Integer codice) {
		this.nome = nome;
		this.cab = cab;
		this.codice = codice;
	}

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

	@Column(name = "codice")
	public Integer getCodice() {
		return this.codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	@Column(name = "cab", nullable = false, length = 5)
	public String getCab() {
		return this.cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

	@Column(name = "last_modify")
	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
}
