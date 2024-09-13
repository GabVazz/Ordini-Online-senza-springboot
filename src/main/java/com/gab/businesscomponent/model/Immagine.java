package com.gab.businesscomponent.model;

import java.io.Serializable;
import java.util.Objects;

public class Immagine implements Serializable {

	private static final long serialVersionUID = 6152171865069661126L;

	private long idImmagine;
	private String url;
	private String descrizione;

	public long getIdImmagine() {
		return idImmagine;
	}

	public void setIdImmagine(long idImmagine) {
		this.idImmagine = idImmagine;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descrizione, idImmagine, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Immagine other = (Immagine) obj;
		return Objects.equals(descrizione, other.descrizione) && idImmagine == other.idImmagine
				&& Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Immagine [idImmagine=" + idImmagine + ", url=" + url + ", descrizione=" + descrizione + "]";
	}

}
