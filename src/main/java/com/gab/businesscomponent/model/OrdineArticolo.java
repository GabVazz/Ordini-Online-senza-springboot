package com.gab.businesscomponent.model;

import java.io.Serializable;
import java.util.Objects;

public class OrdineArticolo implements Serializable {

	private static final long serialVersionUID = -82110213578611958L;

	private long idOrdine;
	private long idArticolo;
	private int qta;

	public long getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
	}

	public long getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(long idArticolo) {
		this.idArticolo = idArticolo;
	}

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArticolo, idOrdine, qta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdineArticolo other = (OrdineArticolo) obj;
		return idArticolo == other.idArticolo && idOrdine == other.idOrdine && qta == other.qta;
	}

	@Override
	public String toString() {
		return "OrdineArticolo [idOrdine=" + idOrdine + ", idArticolo=" + idArticolo + ", qta=" + qta + "]";
	}
}
