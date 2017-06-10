package pe.edu.unmsm.sisdisdoc.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Ciclos")
public class Ciclos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "IDCiclo")
	private int IDCiclo;
	
	@Column(name = "nombre")
	private String nombre;
	
	/*@OneToMany(mappedBy = "escuela", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<CursosDisponibles> cursosDisponibles = new ArrayList<>();*/

	public int getIDCiclo() {
		return IDCiclo;
	}

	public void setIDCiclo(int iDCiclo) {
		IDCiclo = iDCiclo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
