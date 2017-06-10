package pe.edu.unmsm.sisdisdoc.models;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class Profesores {
		
	private int IDProfesor;
		
	private int IDModalidad;
		
	private String codigo;
		
	private String nombres;
		
	private String apellidoPaterno;
		
	private String apellidoMaterno;
	
	private int registroDisponibilidadHoraria;
	
	private int registroCursos;
		
	private int editoPerfil;
		
	@Size(min=7, max=9)
	private String telefono;
		
	@Email
	private String correo;
		
	private String password;
	
	private Modalidades modalidad;
	
	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Modalidades getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidades modalidad) {
		this.modalidad = modalidad;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profesores(){
		
	}

	public int getIDProfesor() {
		return IDProfesor;
	}

	public void setIDProfesor(int iDProfesor) {
		IDProfesor = iDProfesor;
	}

	public int getIDModalidad() {
		return IDModalidad;
	}

	public void setIDModalidad(int iDModalidad) {
		IDModalidad = iDModalidad;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getRegistroDisponibilidadHoraria() {
		return registroDisponibilidadHoraria;
	}

	public void setRegistroDisponibilidadHoraria(int registroDisponibilidadHoraria) {
		this.registroDisponibilidadHoraria = registroDisponibilidadHoraria;
	}

	public int getRegistroCursos() {
		return registroCursos;
	}

	public void setRegistroCursos(int registroCursos) {
		this.registroCursos = registroCursos;
	}

	public int getEditoPerfil() {
		return editoPerfil;
	}

	public void setEditoPerfil(int editoPerfil) {
		this.editoPerfil = editoPerfil;
	}

	@Override
	public String toString() {
		return "Profesores [IDProfesor=" + IDProfesor + ", IDModalidad=" + IDModalidad + ", codigo=" + codigo
				+ ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
				+ apellidoMaterno + ", registroDisponibilidadHoraria=" + registroDisponibilidadHoraria
				+ ", registroCursos=" + registroCursos + ", editoPerfil=" + editoPerfil + ", telefono=" + telefono
				+ ", correo=" + correo + ", password=" + password + ", modalidad=" + modalidad + "]";
	}



}
