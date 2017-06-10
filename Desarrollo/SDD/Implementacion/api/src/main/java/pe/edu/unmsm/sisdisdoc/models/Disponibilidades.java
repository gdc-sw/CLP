package pe.edu.unmsm.sisdisdoc.models;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Disponibilidades {
	private int idprofesor;
	private int idintervalo_hora;
	private int idciclo;
	
	private Profesores profesor;
	private IntervaloHoras intervalo_hora;
	private Ciclos ciclo;
	
	public Profesores getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesores profesor) {
		this.profesor = profesor;
	}
	public IntervaloHoras getIntervalo_hora() {
		return intervalo_hora;
	}
	public void setIntervalo_hora(IntervaloHoras intervalo_hora) {
		this.intervalo_hora = intervalo_hora;
	}
	public Ciclos getCiclo() {
		return ciclo;
	}
	public void setCiclo(Ciclos ciclo) {
		this.ciclo = ciclo;
	}
	public int getIdprofesor() {
		return idprofesor;
	}
	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
	}
	public int getIdintervalo_hora() {
		return idintervalo_hora;
	}
	public void setIdintervalo_hora(int idintervalo_hora) {
		this.idintervalo_hora = idintervalo_hora;
	}
	private String dia;
	public int getIdciclo() {
		return idciclo;
	}
	public void setIdciclo(int idciclo) {
		this.idciclo = idciclo;
	}	
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	@Override
	public String toString() {
		return "Disponibilidades [idprofesor=" + idprofesor + ", idintervalo_hora=" + idintervalo_hora + ", idciclo="
				+ idciclo + ", dia=" + dia + "]";
	}
		
	
	

}
