package com.example.demo.model.DTO;

public class UsuarioDTO {
	
	private Long cve_personal;

	private String nom_primer_apellido;

	private String nom_segundo_apellido;

	private String nom_nombre;
	
	private String matricula;
	
	private String nombreCompleto;
	
	private String usuario;
	
	private String email;
	
	private String des_password;
	
	private String des_unidad_medica;
	
	private boolean ind_estatus;
	
	private String des_puesto;

	private String des_escuela_procedencia;
	
	private String fec_alta;
	
	private String fec_actualizacion;
	
	private String fec_baja;
	
	private Integer ind_num_intentos;
	
	private RolDTO adtc_roles;	
	

	public UsuarioDTO() {

	}

	public UsuarioDTO(Long cve_personal, String nom_primer_apellido, String nom_segundo_apellido, String nom_nombre,
			String matricula, String nombreCompleto, String usuario, String email, String des_password,
			String des_unidad_medica, boolean ind_estatus, String des_puesto, String des_escuela_procedencia,
			String fec_alta, String fec_actualizacion, String fec_baja, Integer ind_num_intentos, RolDTO adtc_roles) {
		super();
		this.cve_personal = cve_personal;
		this.nom_primer_apellido = nom_primer_apellido;
		this.nom_segundo_apellido = nom_segundo_apellido;
		this.nom_nombre = nom_nombre;
		this.matricula = matricula;
		this.nombreCompleto = nombreCompleto;
		this.usuario = usuario;
		this.email = email;
		this.des_password = des_password;
		this.des_unidad_medica = des_unidad_medica;
		this.ind_estatus = ind_estatus;
		this.des_puesto = des_puesto;
		this.des_escuela_procedencia = des_escuela_procedencia;
		this.fec_alta = fec_alta;
		this.fec_actualizacion = fec_actualizacion;
		this.fec_baja = fec_baja;
		this.ind_num_intentos = ind_num_intentos;
		this.adtc_roles = adtc_roles;
	}

	public Long getCve_personal() {
		return cve_personal;
	}

	public void setCve_personal(Long cve_personal) {
		this.cve_personal = cve_personal;
	}

	public String getNom_primer_apellido() {
		return nom_primer_apellido;
	}

	public void setNom_primer_apellido(String nom_primer_apellido) {
		this.nom_primer_apellido = nom_primer_apellido;
	}

	public String getNom_segundo_apellido() {
		return nom_segundo_apellido;
	}

	public void setNom_segundo_apellido(String nom_segundo_apellido) {
		this.nom_segundo_apellido = nom_segundo_apellido;
	}

	public String getNom_nombre() {
		return nom_nombre;
	}

	public void setNom_nombre(String nom_nombre) {
		this.nom_nombre = nom_nombre;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDes_password() {
		return des_password;
	}

	public void setDes_password(String des_password) {
		this.des_password = des_password;
	}

	public String getDes_unidad_medica() {
		return des_unidad_medica;
	}

	public void setDes_unidad_medica(String des_unidad_medica) {
		this.des_unidad_medica = des_unidad_medica;
	}

	public boolean isInd_estatus() {
		return ind_estatus;
	}

	public void setInd_estatus(boolean ind_estatus) {
		this.ind_estatus = ind_estatus;
	}

	public String getDes_puesto() {
		return des_puesto;
	}

	public void setDes_puesto(String des_puesto) {
		this.des_puesto = des_puesto;
	}

	public String getDes_escuela_procedencia() {
		return des_escuela_procedencia;
	}

	public void setDes_escuela_procedencia(String des_escuela_procedencia) {
		this.des_escuela_procedencia = des_escuela_procedencia;
	}

	public String getFec_alta() {
		return fec_alta;
	}

	public void setFec_alta(String fec_alta) {
		this.fec_alta = fec_alta;
	}

	public String getFec_actualizacion() {
		return fec_actualizacion;
	}

	public void setFec_actualizacion(String fec_actualizacion) {
		this.fec_actualizacion = fec_actualizacion;
	}

	public String getFec_baja() {
		return fec_baja;
	}

	public void setFec_baja(String fec_baja) {
		this.fec_baja = fec_baja;
	}

	public Integer getInd_num_intentos() {
		return ind_num_intentos;
	}

	public void setInd_num_intentos(Integer ind_num_intentos) {
		this.ind_num_intentos = ind_num_intentos;
	}

	public RolDTO getAdtc_roles() {
		return adtc_roles;
	}

	public void setAdtc_roles(RolDTO adtc_roles) {
		this.adtc_roles = adtc_roles;
	}

}



