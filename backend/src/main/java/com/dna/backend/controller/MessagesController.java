package com.dna.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;

import com.dna.backend.modle.Messages;
import com.dna.backend.repository.MessageRepository;
import com.dna.backend.service.MessagesService;

/*
 * Message Controller class to deal with all business logic with respective to 
 * MESSAGE Model
 * 
 * @author Jamuna
 * @version 1.1
 * @since 2021/01/22
 * 
 */

@RestController
@RequestMapping("/messages")

/*
 * This is Rest call which will upload and download to Message
 * 
 * @param MultipartFile file
 * 
 * @retun List<Message>
 * 
 */

public class MessagesController {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	MessagesService messageService;

	@GetMapping("/{id}")
	public Messages getMessage(@PathVariable Integer id) {
		return messageRepository.findById(id).orElse(null);
	}

	@PostMapping("/")
	public Messages postUser(@RequestBody Messages messages) {
		return messageRepository.save(messages);
	}

	@PostMapping("/uploaddata")
	public List<Messages> uploaddata(@RequestParam("file") MultipartFile file) throws IOException {

		return messageService.readFile(file);
	}

	/*
	 * This is Rest call which will send CSV formatted data of MESSAGE
	 * 
	 * @param none
	 * 
	 * @retun ResponseEntity<Resource>, media type application/csv
	 * 
	 */

	@GetMapping("/download")
	public List<Messages> displaydata() {
		return messageRepository.findAll();
	}

	@GetMapping("/messages/export")
	public ResponseEntity<Resource> getMessagecsv() throws IOException {
		InputStreamResource isr;
		isr = messageService.fileDownload();

		return ResponseEntity.ok() // checking status
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filenname=messageswritten.csv")
				.contentType(MediaType.parseMediaType("application/csv")).body(isr);

	}


	@DeleteMapping(path = "/{id}")
	public void deleteuser(@PathVariable Integer id) {
		messageRepository.deleteById(id);

	}

	@PutMapping("/{id}")
	public Messages putUser(@PathVariable Integer id, @RequestBody Messages messages) {
		Messages message = messageRepository.findById(id).orElse(null);
		if (message != null) {
			message.setValue(messages.getValue());
			message.setEmails(messages.getEmails());
			message.setAddress(messages.getAddress());
			message.setPhoneno(messages.getPhoneno());
			message.setName(messages.getName());
			return messageRepository.save(message);
		} else {
			return messageRepository.save(messages);
		}

	}
	// Practical explanation is necessary:
	// Put, Patch, and Post make differences whenever we are sending request to web

	@PatchMapping("/{id}")
	public Messages patchUser(@PathVariable Integer id, @RequestBody Messages messages) {
		Messages message = messageRepository.findById(id).orElse(null);
		if (message != null) {
			message.setPhoneno(messages.getPhoneno());
			// message.setEmails(messages.getEmails());
			return messageRepository.save(message);
		} else {
			return messageRepository.save(messages);
		}

	}
}

