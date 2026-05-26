package itch.tsp.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "proyecto_banco")
public class ProyectoBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto_banco")
    private Integer idProyectoBanco;

    private String nombreProyecto;
    @Column(name = "objetivo", columnDefinition = "TEXT")
    private String objetivo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    private String periodo;
    private String tipoProyecto;
    private Integer estatus;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    public Integer getIdProyectoBanco() {
        return idProyectoBanco;
    }

    public void setIdProyectoBanco(Integer idProyectoBanco) {
        this.idProyectoBanco = idProyectoBanco;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }



    public String getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(String tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "ProyectoBanco [id=" + idProyectoBanco +
                ", nombre=" + nombreProyecto +
                ", periodo=" + periodo + "]";
    }
}