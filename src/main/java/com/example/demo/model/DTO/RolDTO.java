package com.example.demo.model.DTO;


public class RolDTO {
	
	private Long cve_rol;	
	
	private String des_rol;	
	
	private String fec_alta;
	
	private String fec_baja;
	
	private String fec_actualizacion;	
	
	private boolean ind_activo;
	
	public RolDTO() {
	}

	public Long getCve_rol() {
		return cve_rol;
	}

	public void setCve_rol(Long cve_rol) {
		this.cve_rol = cve_rol;
	}

	public String getDes_rol() {
		return des_rol;
	}

	public void setDes_rol(String des_rol) {
		this.des_rol = des_rol;
	}

	public String getFec_alta() {
		return fec_alta;
	}

	public void setFec_alta(String fec_alta) {
		this.fec_alta = fec_alta;
	}

	public String getFec_baja() {
		return fec_baja;
	}

	public void setFec_baja(String fec_baja) {
		this.fec_baja = fec_baja;
	}

	public String getFec_actualizacion() {
		return fec_actualizacion;
	}

	public void setFec_actualizacion(String fec_actualizacion) {
		this.fec_actualizacion = fec_actualizacion;
	}

	public boolean isInd_activo() {
		return ind_activo;
	}

	public void setInd_activo(boolean ind_activo) {
		this.ind_activo = ind_activo;
	}


}
