package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.DTO.ReporteDTO;

public interface ReporteServiceAPI {
	ReporteDTO obtenerReporte(Map<String, Object> params)throws Exception;

}
