package com.dna.backend.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dna.backend.modle.Role;
import com.dna.backend.repository.RoleRepository;

/* Role service class to deal with all business logic with respective 
 * to ROLE model
 * 
 * @author Naina
 * @version 1.0
 * @since 2021/01/22
 * 
 * */

@Service
public class RoleService {

	/*
	 * Injecting RoleRepository
	 */
	@Autowired
	private RoleRepository roleRepository;

	/*
	 * This is the read file method which will read csv file and saving role to
	 * database
	 * 
	 * @param MultipartFile file
	 * 
	 * @return list of roles
	 * 
	 */

	public List<Role> readFile(MultipartFile file) throws IOException {
		List<Role> roleList = new ArrayList<Role>();
		String readLine;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
		try {
			// reading file's contents line by line and split words by comma(,) with store
			// in data array
			while ((readLine = bufferedReader.readLine()) != null) {
				String[] data = readLine.split(",");
				Role role = new Role();
				role.setRoleName(data[0]);
				// role.setRoleConfig(data[2]);
				roleList.add(role);
				roleRepository.save(role);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception in StudentService readFile() method");
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return roleList;
	}

	/*
	 * This is the file download method which will read roles from database and
	 * return InputStreamResource
	 * 
	 * @param none
	 * 
	 * @return InputStreamResource
	 * 
	 */
	public InputStreamResource fileDownload() throws IOException {
		List<Role> roleList = roleRepository.findAll();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outSteam), CSVFormat.DEFAULT);

		for (Role role : roleList) {
			List<String> data = new ArrayList<String>();
			data.add(Integer.toString(role.getRoleId()));
			data.add(role.getRoleName());
			csvPrinter.printRecord(data);
		}
		csvPrinter.close();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(outSteam.toByteArray());
		return new InputStreamResource(inputStream);
	}

}
