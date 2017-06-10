package pe.edu.unmsm.sisdisdoc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Modalidades")
public class Modalidades {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "IDModalidad")
	private int IDModalidad;
	
	@Column(name = "tipo")
	private String tipo;
	
	private int cantidad_cursos;

	public Modalidades(){
		
	}
	
	@Override
	public String toString() {
		return "Modalidades [IDModalidad=" + IDModalidad + ", tipo=" + tipo + "]";
	}

	public Modalidades(int iDModalidad, String tipo) {
		super();
		IDModalidad = iDModalidad;
		this.tipo = tipo;
	}

	public int getIDModalidad() {
		return IDModalidad;
	}

	public void setIDModalidad(int iDModalidad) {
		IDModalidad = iDModalidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad_cursos() {
		return cantidad_cursos;
	}

	public void setCantidad_cursos(int cantidad_cursos) {
		this.cantidad_cursos = cantidad_cursos;
	}
	
	
}
