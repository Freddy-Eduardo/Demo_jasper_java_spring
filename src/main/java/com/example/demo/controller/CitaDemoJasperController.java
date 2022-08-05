package com.example.demo.controller;

import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.commons.TipoReporteEnum;
import com.example.demo.model.DTO.ReporteDTO;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.ParticipanteRepository;
import com.example.demo.service.ReporteServiceAPI;
import com.example.demo.util.Crypto;

import org.springframework.core.io.InputStreamResource;

@RestController
@RequestMapping("/api")
public class CitaDemoJasperController {

	@Autowired
	private ReporteServiceAPI reporteVentasServiceAPI;
	
	@Autowired
	CitaRepository citaRepository; 
	
	@Autowired
	ParticipanteRepository participanteRepository;
	
	@Value("${secret.token}")
	private String secretToken;

	@GetMapping(path = "/download/{formato}/cita/{id}/genera/{idUsuario}")
	public ResponseEntity<InputStreamResource> download(@PathVariable(name=  "formato" ) String formato, @PathVariable(name=  "id" ) Long id, @PathVariable(name=  "idUsuario" ) Long idUsuario) {
		ReporteDTO dto = null;
		MediaType mediaType = null;
		InputStreamResource streamResource = null;
		Map<String, Object> params = new  HashMap<String, Object>();

		try {	
			 params.put("idUsuario", idUsuario);
			 params.put("tipo", formato);
			 params.put("id", id);
			 
			 dto = reporteVentasServiceAPI.obtenerReporte(params);
			 streamResource = new InputStreamResource(dto.getStream());
			
			if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			} else {
				mediaType = MediaType.APPLICATION_PDF;
			} 
		
		}catch (Exception e) {
			System.out.println("error al generar la cita "+ e.getMessage());
			return null;
		}

		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
	
	@GetMapping("/token/{text}")
	public String tokenEncode(@PathVariable ("text") String txt){
		String token = null;
		
		try {
			token = Crypto.tokenOneHour(txt, secretToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return token;
	}
	
	@GetMapping("/decodeToken")
	public String decodeToken(@RequestParam String token){
		String textDecode = null;
		try {
			textDecode = Crypto.decryptToken(token , secretToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return textDecode;
	}
	

}
