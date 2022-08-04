package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ADTST_PARTICIPANTES")
public class Participante implements Serializable{

	private static final long serialVersionUID = 2960439031418645683L;
	public static final Integer EsPeciente = 1;
	public static final Integer EsNucleFamiliar = 2;
	public static final Integer EsOtros = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PARTICIPANTE")
	private Long cveParticipante;
	
	@Column(name = "NUM_NSS", nullable = false)
	private Long nss;
	
	@Column(name = "NOM_COMPLETO", length = 500, nullable = false)
	private String nombreCompleto;
	
	@Column(name = "CVE_TIPO_PACIENTE", nullable = false)
	private Integer tipoParticipante;
	
	@Column(name = "FEC_EXPIRA", nullable = true)
	@Temporal (TemporalType.TIMESTAMP)
	private Date fechaExpira;
	
	@Column(name = "FEC_ALTA", nullable = true)
	@Temporal (TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	@Column(name = "FEC_BAJA", nullable = true)
	@Temporal (TemporalType.TIMESTAMP)
	private Date fechaBaja;
	
	@Column(name = "IND_ACTIVO", nullable = true)
	private Boolean activo;
	
	@Column(name = "FEC_ACTUALIZACION", nullable = true)
	@Temporal (TemporalType.TIMESTAMP)
	private Date fecActualizacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CITA")
	Cita cita;

	public Participante() {	}

	public Participante(Long cveParticipante, Long nss, String nombreCompleto, Integer tipoParticipante,
			Date fechaExpira, Date fechaAlta, Date fechaBaja, Boolean activo, Date fecActualizacion, Cita cita) {
		super();
		this.cveParticipante = cveParticipante;
		this.nss = nss;
		this.nombreCompleto = nombreCompleto;
		this.tipoParticipante = tipoParticipante;
		this.fechaExpira = fechaExpira;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.activo = activo;
		this.fecActualizacion = fecActualizacion;
		this.cita = cita;
	}

	public Long getCveParticipante() {
		return cveParticipante;
	}

	public void setCveParticipante(Long cveParticipante) {
		this.cveParticipante = cveParticipante;
	}

	public Long getNss() {
		return nss;
	}

	public void setNss(Long nss) {
		this.nss = nss;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Integer getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante(Integer tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}

	public Date getFechaExpira() {
		return fechaExpira;
	}

	public void setFechaExpira(Date fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFecActualizacion() {
		return fecActualizacion;
	}

	public void setFecActualizacion(Date fecActualizacion) {
		this.fecActualizacion = fecActualizacion;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Integer getEspeciente() {
		return EsPeciente;
	}

	public static Integer getEsnuclefamiliar() {
		return EsNucleFamiliar;
	}

	public static Integer getEsotros() {
		return EsOtros;
	}

}
