package com.gab.businesscomponent.model;

import java.io.Serializable;
import java.util.Objects;

public class Articolo implements Serializable {

	private static final long serialVersionUID = -8713310910217485459L;
	private long idArticolo;
	private String marca;
	private String modello;
	private double prezzo;

	public long getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(long idArticolo) {
		this.idArticolo = idArticolo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArticolo, marca, modello, prezzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		return idArticolo == other.idArticolo && Objects.equals(marca, other.marca)
				&& Objects.equals(modello, other.modello)
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo);
	}

	@Override
	public String toString() {
		return "Articolo [idArticolo=" + idArticolo + ", marca=" + marca + ", modello=" + modello + ", prezzo=" + prezzo
				+ "]";
	}
}
