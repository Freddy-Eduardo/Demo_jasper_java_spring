package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;

@Entity
@Table(name = "ADTST_CITAS")
public class Cita implements Serializable{
	private static final long serialVersionUID = 1588332417763092687L;
	//ESTATUS OCIONES DEL SERVICIO
	public static final String Inicial     = "Inicial";
	public static final String Seguimiento = "Seguimiento";
	//ESTATUS MODALIDAD DE LA CITA
	public static final String Presencial  = "Presencial";
	public static final String Distancia = "Distancia";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CITA")
	private Long cveCita;
	
	@Column(name = "ID_CLAVE_CALENDARIO_ANUAL", nullable = true)
	private int cveCalendarioAnual;
	
	@Column(name = "NUM_NSS", length = 11, nullable = false)
	private Long nss;
	
	@Column(name = "DES_SERVICIO", length = 500, nullable = false)				//CRITERIO DE BUSQUEDA
	private String descripcionServicio;
	
	@Column(name = "DES_GRUPO_PROGRAMA", length = 45, nullable = false)			//CRITERIO DE BUSQUEDA
	private String grupoPrograma;
	
	@Column(name = "FEC_INICIO", length = 45, nullable = false)					//CRITERIO DE BUSQUEDA
	@Temporal (TemporalType.DATE)
	private Date fechaInicio;

	@Column(name = "TIM_HORA_INICIO", length = 45, nullable = false)
	@Temporal (TemporalType.TIME)
	private Date horaInicio;
	
	@Column(name = "FEC_FIN", length = 45, nullable = false)
	@Temporal (TemporalType.DATE)
	private Date fechaFin;
	
	@Column(name = "TIM_HORA_FIN", length = 45, nullable = false)
	@Temporal (TemporalType.TIME)
	private Date horaFin;
	
	@Column(name = "NUM_DURACION", length = 45, nullable = false)
	private String duracion;

	@Column(name = "DES_UBICACION", length = 700, nullable = false)				//CRITERIO DE BUSQUEDA
	private String ubicacion;
	
	@Column(name = "DES_TRABAJADOR_SOCIAL", length = 100, nullable = false)		//CRITERIO DE BUSQUEDA
	private String trabajadorSocial;
	
	@Column(name = "DES_UNIDAD_MEDICA", length = 1000, nullable = false)
	private String unidadMedica;
	
	@Column(name = "DOM_DIRECCION", length = 700, nullable = false)
	private String direccion;

	@Column(name = "DES_TURNO", length = 50, nullable = false)					//CRITERIO DE BUSQUEDA
	private String turno;
	
	@Column(name = "DES_OCASION_SERVICIO", length = 45, nullable = false)
	private String ocasionServicio;
	
	@Column(name = "DES_MODALIDAD", length = 45, nullable = false)
	private String modalidad;
	
	@Column(name = "DES_TIPO_CITA", length = 45, nullable = false)
	private String tipoCita;
	
	@Column(name = "NUM_PARTICIPANTES_CONFIRMADOS", length = 10, nullable = true)
	private Integer participantesConfirmados;
	
	@Column(name = "DES_STATUS_CITA", length = 45, nullable = false)
	private String estatus;	
	
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
	
	@Transient
	private List<Map<String,Object>> participante;
	
	public Cita() {}
    
	public Cita(Long cveCita, int cveCalendarioAnual, Long nss, String descripcionServicio, String grupoPrograma,
			Date fechaInicio, Date horaInicio, Date fechaFin, Date horaFin, String duracion, String ubicacion,
			String trabajadorSocial, String unidadMedica, String direccion, String turno, String ocasionServicio,
			String modalidad, String tipoCita, Integer participantesConfirmados, String estatus, Date fechaExpira,
			Date fechaAlta, Date fechaBaja, Boolean activo, Date fecActualizacion) {
		super();
		this.cveCita = cveCita;
		this.cveCalendarioAnual = cveCalendarioAnual;
		this.nss = nss;
		this.descripcionServicio = descripcionServicio;
		this.grupoPrograma = grupoPrograma;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.fechaFin = fechaFin;
		this.horaFin = horaFin;
		this.duracion = duracion;
		this.ubicacion = ubicacion;
		this.trabajadorSocial = trabajadorSocial;
		this.unidadMedica = unidadMedica;
		this.direccion = direccion;
		this.turno = turno;
		this.ocasionServicio = ocasionServicio;
		this.modalidad = modalidad;
		this.tipoCita = tipoCita;
		this.participantesConfirmados = participantesConfirmados;
		this.estatus = estatus;
		this.fechaExpira = fechaExpira;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.activo = activo;
		this.fecActualizacion = fecActualizacion;
	}

	public Long getCveCita() {
		return cveCita;
	}

	public void setCveCita(Long cveCita) {
		this.cveCita = cveCita;
	}

	public int getCveCalendarioAnual() {
		return cveCalendarioAnual;
	}

	public void setCveCalendarioAnual(int cveCalendarioAnual) {
		this.cveCalendarioAnual = cveCalendarioAnual;
	}

	public Long getNss() {
		return nss;
	}

	public void setNss(Long nss) {
		this.nss = nss;
	}

	public String getDescripcionServicio() {
		return descripcionServicio;
	}

	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}

	public String getGrupoPrograma() {
		return grupoPrograma;
	}

	public void setGrupoPrograma(String grupoPrograma) {
		this.grupoPrograma = grupoPrograma;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTrabajadorSocial() {
		return trabajadorSocial;
	}

	public void setTrabajadorSocial(String trabajadorSocial) {
		this.trabajadorSocial = trabajadorSocial;
	}

	public String getUnidadMedica() {
		return unidadMedica;
	}

	public void setUnidadMedica(String unidadMedica) {
		this.unidadMedica = unidadMedica;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getOcasionServicio() {
		return ocasionServicio;
	}

	public void setOcasionServicio(String ocasionServicio) {
		this.ocasionServicio = ocasionServicio;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getTipoCita() {
		return tipoCita;
	}

	public void setTipoCita(String tipoCita) {
		this.tipoCita = tipoCita;
	}

	public int getParticipantesConfirmados() {
		return participantesConfirmados;
	}

	public void setParticipantesConfirmados(Integer participantesConfirmados) {
		this.participantesConfirmados = participantesConfirmados;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
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

	public List<Map<String,Object>> getParticipante() {
		return participante;
	}

	public void setParticipante(List<Map<String,Object>> participante) {
		this.participante = participante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
