package itch.tsp.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "asesor_interno")
public class AsesorInterno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_asesor_interno")
	private Integer idAsesorInterno;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "correo")
	private String correo;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "foto")
	private String foto;

	@Column(name = "estatus")
	private Integer estatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_departamento_academico")
	private DepartamentoAcademico departamentoAcademico;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Integer getIdAsesorInterno() {
		return idAsesorInterno;
	}

	public void setIdAsesorInterno(Integer idAsesorInterno) {
		this.idAsesorInterno = idAsesorInterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DepartamentoAcademico getDepartamentoAcademico() {
		return departamentoAcademico;
	}

	public void setDepartamentoAcademico(DepartamentoAcademico departamentoAcademico) {
		this.departamentoAcademico = departamentoAcademico;
	}

	@Override
	public String toString() {
		return "AsesorInterno [idAsesorInterno=" + idAsesorInterno + ", nombre=" + nombre + ", apellidoPaterno="
				+ apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo + ", telefono="
				+ telefono + ", foto=" + foto + ", estatus=" + estatus + ", departamentoAcademico="
				+ departamentoAcademico + ", usuario=" + usuario + "]";
	}
}