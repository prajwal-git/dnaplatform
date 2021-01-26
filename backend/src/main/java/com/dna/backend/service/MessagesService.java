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

import com.dna.backend.modle.Messages;
import com.dna.backend.repository.MessageRepository;


/*
 * Message service class to deal with all business logic with respective to 
 * MESSAGE Model
 * 
 * @author Jamuna
 * @version 1.0
 * @since 2021/01/22
 * 
 */

@Service
public class MessagesService {
	
	/* Injecting  MessageRepository
	 * 
	 * */
	

	@Autowired
	private MessageRepository messageRepository;
	
	/* This is Read File method which will read CSV file and saving Message to database
	 * 
	 * @param MultipartFile file
	 * 
	 * @retun List of Message
	 * 
	 * */

	public List<Messages> readFile(MultipartFile file) throws IOException { 
		String readLine;
		BufferedReader bufferedReader = null;
	
		List<Messages> messageList = new ArrayList<Messages>();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));

			// reading file's contents line by line and split words by comma(,) with store
			// in data array
			while ((readLine = bufferedReader.readLine()) != null) {
				String[] data = readLine.split(",");
				Messages messages = new Messages();
				messages.setName(data[0]);
				messages.setValue(data[1]);
				messages.setEmails(data[2]);
				messages.setAddress(data[3]);
				messages.setPhoneno(Integer.parseInt(data[4]));

				messageList.add(messages);
				messageRepository.save(messages);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception in MessageService readFile() method");
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return messageList;
	}

	/* This is the file download method which will read Messages from database and returning InputStreamResource
	 * 
	 * @param none
	 * 
	 * @retun InputStreamResource
	 * 
	 * */
	
	public InputStreamResource fileDownload() throws IOException {

		List<Messages> messageList = messageRepository.findAll();

		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outSteam), CSVFormat.DEFAULT);

		for (Messages msgs : messageList) {

			List<String> msgdata = new ArrayList<String>();
			msgdata.add(msgs.getName());
			msgdata.add(msgs.getEmails());
			msgdata.add(msgs.getAddress());
			msgdata.add(msgs.getValue());

			csvPrinter.printRecord(msgdata);
		}

		csvPrinter.flush();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(outSteam.toByteArray());

		return new InputStreamResource(inputStream);

	}
}
