package itch.tsp.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento_academico")
public class DepartamentoAcademico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departamento_academico")
	private Integer idDepartamentoAcademico;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "estatus")
	private Integer estatus;

	public Integer getIdDepartamentoAcademico() {
		return idDepartamentoAcademico;
	}

	public void setIdDepartamentoAcademico(Integer idDepartamentoAcademico) {
		this.idDepartamentoAcademico = idDepartamentoAcademico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "DepartamentoAcademico [idDepartamentoAcademico=" + idDepartamentoAcademico
				+ ", nombre=" + nombre + ", estatus=" + estatus + "]";
	}
	
}