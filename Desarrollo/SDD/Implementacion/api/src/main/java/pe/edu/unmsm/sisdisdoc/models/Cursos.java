package pe.edu.unmsm.sisdisdoc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class Cursos {
		
	private int IDCurso;
		
	private String nombre;
		
	private Escuelas escuela;
	
	public Cursos(){
				
	}

	public Cursos(int IDCurso, String nombre){
		this.IDCurso = IDCurso;
		this.nombre = nombre;
	}
	public Escuelas getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuelas escuela) {
		this.escuela = escuela;
	}	

	public int getIDCurso() {
		return IDCurso;
	}

	public void setIDCurso(int iDCurso) {
		IDCurso = iDCurso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IDCurso;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Cursos other = (Cursos) obj;
		if (IDCurso != other.IDCurso)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cursos [IDCurso=" + IDCurso + ", nombre=" + nombre + "]";
	}
	
		
	
	

}
