package com.example.demo.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import com.example.demo.commons.JasperReportManager;
import com.example.demo.commons.TipoReporteEnum;
import com.example.demo.model.Cita;
import com.example.demo.model.Participante;
import com.example.demo.model.DTO.BeneficiariosResponseDTO;
import com.example.demo.model.DTO.ReporteDTO;
import com.example.demo.model.DTO.UsuarioDTO;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.ParticipanteRepository;
import com.example.demo.service.ReporteServiceAPI;
import com.example.demo.util.DateTime;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReporteSeriviceImpl implements ReporteServiceAPI{
	@Autowired
	private JasperReportManager reportManager;
	
	@Autowired
	CitaRepository citaRepository; 
	@Autowired
	ParticipanteRepository participanteRepository;
	
	@Autowired
	RestTemplate restTemplate;

	@Value("${api.usuarios}")
	private String UrlUsuarios;
	
	@Value("${api.pacientes}")
	private String urlPacientes;
	
	@Value("${api.ubicaciones}")
	private String urlUbicaciones;

	@Override
	public ReporteDTO obtenerReporte (Map<String, Object> params) throws Exception{
		Long id = Long.parseLong(params.get("id").toString());
		ReporteDTO dto = new ReporteDTO();
		String fileName = "Blank_A4";
		
		UsuarioDTO empleado = infoEmpleado( Long.parseLong( params.get("idUsuario").toString() ));
		
		if(empleado == null) {
			throw new Exception("Error usuario no encontrado");
		}
		
		params.put("fechaActual", DateTime.dayOfWeekString(DateTime.now())+", " + DateTime.dateText(DateTime.now()));

		
		
		params.put("nombreTrabajadorLogin", empleado.getNom_nombre()+" "+empleado.getNom_primer_apellido()+" "+empleado.getNom_segundo_apellido());
		params.put("matriculaTrabajadorLogin", empleado.getMatricula());
		
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx" : ".pdf"; 
		
		Cita citaModel = citaRepository.findById(id).orElse(null);
		Participante participanteModel = participanteRepository.pacientePrincipalbyCita(id, Participante.EsPeciente);
		
		BeneficiariosResponseDTO beneficiario = beneficiario(citaModel.getNss());
		
		@SuppressWarnings("unchecked")
		List<HashMap<String ,Object>> listBeneficiario = (List<HashMap<String ,Object>>) beneficiario.getBusquedanss().get("beneficiarios");
		
		//Busqueda de información del paciente
		if( listBeneficiario.size() > 0  ) {
			for(int bnf = 0; bnf < listBeneficiario.size()  ; bnf++) {
				if( listBeneficiario.get(bnf).get("paciente").toString().contains(participanteModel.getNombreCompleto())) {
					
					
					//listBeneficiario.get(bnf).get("unidadMedica");			
					params.put("nombrePaciente", listBeneficiario.get(bnf).get("paciente"));
					params.put("curpPaciente", 	listBeneficiario.get(bnf).get("curp") );
					params.put("consultorio", listBeneficiario.get(bnf).get("consultorio") );
					params.put("aMedico", listBeneficiario.get(bnf).get("agregadoMedico"));					
					params.put("turno", listBeneficiario.get(bnf).get("turno"));
					
					params.put("ooad", "Freddy");
					params.put("cvePtal", "Freddy");
					
					System.out.println("datos de prueba =>" +listBeneficiario.get(bnf));
					break;
				}
			}
		}
	    
	    /**
	     *===============================================
	     * Información de la cita formato de campos
	     *===============================================
	     */
	    params.put("fechaInicio",DateTime.dateFormat(citaModel.getFechaInicio(), "dd/MM/yyyy"));
	    params.put("citaDiaSemana", DateTime.dayOfWeekString(citaModel.getFechaInicio())+ " "+DateTime.formatTimeAMAndPM(citaModel.getHoraInicio())+" - "+DateTime.formatTimeAMAndPM(citaModel.getHoraFin()) );
		params.put("citas",DateTime.dayOfWeekString(citaModel.getFechaInicio())+ " "+DateTime.dateFormat(citaModel.getFechaInicio(), "dd/MM/yyyy")+" "+DateTime.formatTimeAMAndPM(citaModel.getHoraInicio()) );
		    
		byte bs[] = reportManager.export(fileName, params.get("tipo").toString(), params,  Arrays.asList(citaModel));

		dto.setStream(new ByteArrayInputStream(bs));
		dto.setFileName(citaModel.getNss()+"_"+DateTime.dateFormat(DateTime.now(), "dd_MM_yyyy") + extension);
		dto.setLength(bs.length);

		return dto;
	}
	
	public UsuarioDTO infoEmpleado( Long id){
		UsuarioDTO usuario = null;
		try {
			usuario =  restTemplate.getForEntity(UrlUsuarios+id, UsuarioDTO.class ).getBody();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return usuario;		
	}
	
	public BeneficiariosResponseDTO beneficiario(Long nss){
		BeneficiariosResponseDTO BeneficiariosResponse = null;
		
		try {
			BeneficiariosResponse =  restTemplate.getForEntity(urlPacientes+nss, BeneficiariosResponseDTO.class ).getBody();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return BeneficiariosResponse;		
	}
	
	public BeneficiariosResponseDTO unidadMedica(String idUnidad){
		BeneficiariosResponseDTO BeneficiariosResponse = null;
		
		try {
			BeneficiariosResponse =  restTemplate.getForEntity(urlUbicaciones+idUnidad, BeneficiariosResponseDTO.class ).getBody();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return BeneficiariosResponse;		
	}
}
