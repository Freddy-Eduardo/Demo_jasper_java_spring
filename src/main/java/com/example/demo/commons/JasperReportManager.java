package com.example.demo.commons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Component
public class JasperReportManager {

	private static final String REPORT_FOLDER = "reports";

	private static final String JASPER = ".jrxml";
	
	/**
	 * 
	 * @param fileName
	 * @param tipoReporte
	 * @param params
	 * @param con
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	public byte[] export(String fileName, String tipoReporte, Map<String, Object> params, Collection<?> dataObj) throws JRException, IOException {

		byte data[] = null;
		ClassPathResource resource = new ClassPathResource(REPORT_FOLDER + File.separator + fileName + JASPER);
		InputStream inputStream = resource.getInputStream();
				
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource( dataObj, false);
		JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, beanCollectionDataSource);
		
		if (tipoReporte.equalsIgnoreCase(TipoReporteEnum.EXCEL.toString())) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			data =	stream.toByteArray();
		} else {
			data = JasperExportManager.exportReportToPdf(jasperPrint);
		}
		
		return data;
	}
	
	

}
