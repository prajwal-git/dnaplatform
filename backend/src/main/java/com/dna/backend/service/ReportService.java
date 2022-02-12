package com.dna.backend.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;

import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/*
 * 
 * 
 * */
@Service
public class ReportService {
	@Autowired
	private UserRepository userRepository;
	/*
	 * created path for all format
	 * 
	 */
	/*
	 * method to export Jasper report get all user from list Interface get file
	 * class path for Jasper Report compiling and ready to generate Data source
	 * implementation that wraps a collection if JavaBean objects
	 */
	@Value("${test}")
	private String ref;
	@Value("${save}")
	private String savefile;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		List<User> user = userRepository.findAll();
		File file = ResourceUtils.getFile(ref);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(user);
		// Mapping the object
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", "Param");
		// printing Jasper report through Jasper Print
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, beanCollectionDataSource);

		// Different format of report creation
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, savefile + "html");

		}

		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, savefile + "pdf");
		}
		if (reportFormat.equalsIgnoreCase("csv")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, savefile + "csv");
		}
		return "file downoaded.";

	}

}
