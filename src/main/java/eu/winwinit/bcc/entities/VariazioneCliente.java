package eu.winwinit.bcc.entities;
// Generated 2-ott-2019 9.39.22 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * VariazioniClienti generated by hbm2java
 */
@Entity
@Table(name = "variazioni_clienti")
public class VariazioneCliente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Cliente clienti;
	private String campo;
	private Utente utenti;
	private Date lastModify;

	public VariazioneCliente() {
	}

	public VariazioneCliente(Cliente clienti, String campo) {
		this.clienti = clienti;
		this.campo = campo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente", nullable = false)
	public Cliente getClienti() {
		return this.clienti;
	}

	public void setClienti(Cliente clienti) {
		this.clienti = clienti;
	}

	@Column(name = "campo", nullable = false, length = 20)
	public String getCampo() {
		return this.campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_modify", nullable = false)
	public Utente getUtenti() {
		return utenti;
	}

	public void setUtenti(Utente utenti) {
		this.utenti = utenti;
	}

	@Column(name = "last_modify")
	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
}