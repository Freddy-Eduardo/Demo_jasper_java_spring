package com.example.demo.controller;

import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CitaDeoController {

	@Autowired
	private ReporteServiceAPI reporteVentasServiceAPI;
	
	@Autowired
	CitaRepository citaRepository; 
	
	@Autowired
	ParticipanteRepository participanteRepository;

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
	
	@GetMapping("/askmdñm")
	public String example(){
		String C = null;
		try {
			C = Crypto.tokenOneHour("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.", "hjasdhjasdasdasdqwe2133");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  C;
	}
	
	@GetMapping("/decode")
	public String decode(@RequestParam String token){
		String DC = null;
		try {
			DC = Crypto.decryptToken(token , "hjasdhjasdasdasdqwe2133");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DC;
	}
	

}
