package com.gab.businesscomponent.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GenericReport {

	private Map<String, Object> columns = new HashMap<String, Object>();

	public void addColumn(String name, Object value) {
		columns.put(name, value);
	}

	public Object getColumn(String name) {
		return columns.get(name);
	}

	public Map<String, Object> getColumns() {
		return columns;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columns);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericReport other = (GenericReport) obj;
		return Objects.equals(columns, other.columns);
	}

	@Override
	public String toString() {
		return "GenericReport [columns=" + columns + "]";
	}
}
