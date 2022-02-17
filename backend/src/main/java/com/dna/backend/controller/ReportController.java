package com.dna.backend.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.backend.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/user/{format}")
	public String getReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return reportService.exportReport(format);

	}

}
