package pe.edu.unmsm.sisdisdoc.models;

public class CursosDisponibles {
	
	private int idprofesor;
	
	private int idcurso;
	
	private int idciclo;
	
	private Cursos curso;

	public Cursos getCurso() {
		return curso;
	}

	public void setCurso(Cursos curso) {
		this.curso = curso;
	}

	public int getIdprofesor() {
		return idprofesor;
	}

	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
	}

	public int getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(int idcurso) {
		this.idcurso = idcurso;
	}

	public int getIdciclo() {
		return idciclo;
	}

	public void setIdciclo(int idciclo) {
		this.idciclo = idciclo;
	}

	@Override
	public String toString() {
		return "CursosDisponibles [idprofesor=" + idprofesor + ", idcurso=" + idcurso + ", idciclo=" + idciclo
				+ ", curso=" + curso + "]";
	}

	
	
	
	
}
