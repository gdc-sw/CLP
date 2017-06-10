package pe.edu.unmsm.sisdisdoc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class IntervaloHoras {
		
	private int IDIntervalo_horas;
		
	private int hora_inicio;
	
	private int hora_fin;


	public IntervaloHoras() {
		
	}

	public int getIDIntervalo_horas() {
		return IDIntervalo_horas;
	}


	public void setIDIntervalo_horas(int iDIntervalo_horas) {
		IDIntervalo_horas = iDIntervalo_horas;
	}


	public int getHora_inicio() {
		return hora_inicio;
	}


	public void setHora_inicio(int hora_inicio) {
		this.hora_inicio = hora_inicio;
	}


	public int getHora_fin() {
		return hora_fin;
	}


	public void setHora_fin(int hora_fin) {
		this.hora_fin = hora_fin;
	}
	
	

}
