package eu.winwinit.bcc.entities;
// Generated 2-ott-2019 9.39.22 by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "clienti", uniqueConstraints = @UniqueConstraint(columnNames = "nag"))
public class Cliente implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private Filiale filiali;
	private String nag;
	private String cab;
	private String nome;
	private Date dataNascita;
	private String telefono;
	private String email;
	private Boolean p1;
	private Boolean p2;
	private Boolean p3;
	private Boolean p4;
	private Boolean p5;
	private Boolean p6;
	private Boolean p7;
	private Boolean firma;
	private String codice;
	private Boolean confermato;
	private Utente utenti;
	private Date lastModify;
	
//	private List<VariazioneCliente> variazioniClienti = new ArrayList<VariazioneCliente>();

	public Cliente() {
	}

	public Cliente(Filiale filiali, String nag, String cab, String nome, Date dataNascita, String codice) {
		this.filiali = filiali;
		this.nag = nag;
		this.cab = cab;
		this.nome = nome;
		this.dataNascita = dataNascita;
		this.codice = codice;
	}

	public Cliente(Filiale filiali, String nag, String cab, String nome, Date dataNascita, String telefono,
			String email, Boolean p1, Boolean p2, Boolean p3, Boolean p4, Boolean p5, Boolean p6, Boolean p7,
			Boolean firma, String codice, Boolean confermato 
//			,ArrayList<VariazioneCliente> variazioniClienti
			) {
		this.filiali = filiali;
		this.nag = nag;
		this.cab = cab;
		this.nome = nome;
		this.dataNascita = dataNascita;
		this.telefono = telefono;
		this.email = email;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
		this.p6 = p6;
		this.p7 = p7;
		this.firma = firma;
		this.codice = codice;
		this.confermato = confermato;
//		this.variazioniClienti = variazioniClienti;
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
	@JoinColumn(name = "filiale", nullable = false)
	public Filiale getFiliali() {
		return this.filiali;
	}

	public void setFiliali(Filiale filiali) {
		this.filiali = filiali;
	}

	@Column(name = "nag", unique = true, nullable = false, length = 20)
	public String getNag() {
		return this.nag;
	}

	public void setNag(String nag) {
		this.nag = nag;
	}

	@Column(name = "cab", nullable = false, length = 5)
	public String getCab() {
		return this.cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

	@Column(name = "nome", nullable = false, length = 30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascita", nullable = false, length = 10)
	public Date getDataNascita() {
		return this.dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	@Column(name = "telefono", length = 12)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "p1")
	public Boolean getP1() {
		return this.p1;
	}

	public void setP1(Boolean p1) {
		this.p1 = p1;
	}

	@Column(name = "p2")
	public Boolean getP2() {
		return this.p2;
	}

	public void setP2(Boolean p2) {
		this.p2 = p2;
	}

	@Column(name = "p3")
	public Boolean getP3() {
		return this.p3;
	}

	public void setP3(Boolean p3) {
		this.p3 = p3;
	}

	@Column(name = "p4")
	public Boolean getP4() {
		return this.p4;
	}

	public void setP4(Boolean p4) {
		this.p4 = p4;
	}

	@Column(name = "p5")
	public Boolean getP5() {
		return this.p5;
	}

	public void setP5(Boolean p5) {
		this.p5 = p5;
	}

	@Column(name = "p6")
	public Boolean getP6() {
		return this.p6;
	}

	public void setP6(Boolean p6) {
		this.p6 = p6;
	}

	@Column(name = "p7")
	public Boolean getP7() {
		return this.p7;
	}

	public void setP7(Boolean p7) {
		this.p7 = p7;
	}

	@Column(name = "firma")
	public Boolean getFirma() {
		return this.firma;
	}

	public void setFirma(Boolean firma) {
		this.firma = firma;
	}

	@Column(name = "codice", nullable = false, length = 25)
	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@Column(name = "confermato")
	public Boolean getConfermato() {
		return this.confermato;
	}

	public void setConfermato(Boolean confermato) {
		this.confermato = confermato;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_modify", nullable = false)
	@JsonIgnore
	public Utente getUtenti() {
		return utenti;
	}

	public void setUtenti(Utente utenti) {
		this.utenti = utenti;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "last_modify")
	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
}
