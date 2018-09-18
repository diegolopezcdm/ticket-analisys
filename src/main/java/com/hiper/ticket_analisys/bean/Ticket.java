package com.hiper.ticket_analisys.bean;

import java.util.Date;

public class Ticket implements Comparable<Ticket>{
	
	private String agencia;
	
	private String tipo;
	
	private Date generado;
	
	private Date asignado;

	
	
	public Ticket(String agencia, String tipo, Date generado, Date asignado) {
		super();
		this.agencia = agencia;
		this.tipo = tipo;
		this.generado = generado;
		this.asignado = asignado;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getGenerado() {
		return generado;
	}

	public void setGenerado(Date generado) {
		this.generado = generado;
	}

	public Date getAsignado() {
		return asignado;
	}

	public void setAsignado(Date asignado) {
		this.asignado = asignado;
	}

	@Override
	public String toString() {
		return "Ticket [agencia=" + agencia + ", tipo=" + tipo + ", generado=" + generado + ", asignado=" + asignado
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((asignado == null) ? 0 : asignado.hashCode());
		result = prime * result + ((generado == null) ? 0 : generado.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (asignado == null) {
			if (other.asignado != null)
				return false;
		} else if (!asignado.equals(other.asignado))
			return false;
		if (generado == null) {
			if (other.generado != null)
				return false;
		} else if (!generado.equals(other.generado))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	public int compareTo(Ticket o) {
		if(this.getGenerado().after(o.getGenerado())){
			return 1;
		} else if (this.getGenerado().before(o.getGenerado())){
			return -1;
		} else {
			return 0;
		}
	}
	
	


}
