package com.gab.businesscomponent.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Ordine implements Serializable {
	private static final long serialVersionUID = -9107689863234697485L;

	private long idOrdine;
	private double totale;
	private Date data;
	private String username;

	public long getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, idOrdine, totale, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		return Objects.equals(data, other.data) && idOrdine == other.idOrdine
				&& Double.doubleToLongBits(totale) == Double.doubleToLongBits(other.totale)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Ordine [idOrdine=" + idOrdine + ", totale=" + totale + ", data=" + data + ", username=" + username
				+ "]";
	}
}
