package com.gab.businesscomponent.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Utente implements Serializable {
	
	private static final long serialVersionUID = -32983924410854296L;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String cap;
	private Date nascita;
	private String username;
	private String password;
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public Date getNascita() {
		return nascita;
	}

	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cap, cognome, email, indirizzo, nascita, nome, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(cap, other.cap) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(email, other.email) && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(nascita, other.nascita) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + ", cap=" + cap
				+ ", nascita=" + nascita + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}

}
