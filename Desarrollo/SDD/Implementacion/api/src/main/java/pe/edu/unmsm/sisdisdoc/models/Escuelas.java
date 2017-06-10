package pe.edu.unmsm.sisdisdoc.models;

import java.util.ArrayList;
import java.util.List;

public class Escuelas {
	
	private int IDEscuela;
		
	private String nombre;
	
	private List<Cursos> cursos;
	
	public Escuelas() {
		
	}

	@Override
	public String toString() {
		return "Escuelas [IDEscuela=" + IDEscuela + ", nombre=" + nombre + ", cursos=" + cursos + "]";
	}

	public Escuelas(int iDEscuela, String nombre) {		
		this.IDEscuela = iDEscuela;
		this.nombre = nombre;
	}
	
	public List<Cursos> getCursos() {
		return cursos;
	}

	public void setCursos(List<Cursos> cursos) {
		this.cursos = cursos;
	}

	public int getIDEscuela() {
		return IDEscuela;
	}

	public void setIDEscuela(int iDEscuela) {
		IDEscuela = iDEscuela;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
