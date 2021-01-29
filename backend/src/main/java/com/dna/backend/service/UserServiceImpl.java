package com.dna.backend.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dna.backend.dto.UserDto;
import com.dna.backend.modle.Role;
import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUserName(), userDto.getFirstName(), userDto.getLastName(),
				userDto.getMiddleName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
				userDto.getAddress(), userDto.getOfficePhone(), userDto.getCellPhone(), userDto.getDob(),
				userDto.isActive(), userDto.getGender(), userDto.getRoles());
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

	/*
	 * This is the read file method which will read csv file and saving role to
	 * database
	 * 
	 * @param MultipartFile file
	 * 
	 * @return list of users
	 */
	public List<User> readFile(MultipartFile file) throws IOException {
		List<User> userList = new ArrayList<User>();
		String readLine;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
		try {
			// reading file's contents line by line and split words by comma(,) with store
			// in data array
			int count = 0;
			while ((readLine = bufferedReader.readLine()) != null) {
				if (count > 0) {
					String[] data = readLine.split(",");
					User user = new User();
					user.setFirstName(data[0]);
					user.setLastName(data[1]);
					user.setMiddleName(data[2]);
					user.setCellPhone(Integer.parseInt(data[3]));
					user.setAddress(data[4]);
					user.setEmail(data[5]);
					user.setActive(Boolean.parseBoolean(data[6]));
					user.setOfficePhone(Integer.parseInt(data[7]));
					user.setPassword(data[8]);
					user.setUserName(data[9]);
					userList.add(user);
					userRepository.save(user);
				}
				count++;
			}
			count = 0;
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception in UserService readFile() method");
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return userList;
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
		List<User> userList = userRepository.findAll();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outSteam), CSVFormat.DEFAULT);

		for (User user : userList) {
			List<String> data = new ArrayList<String>();
			data.add(user.getFirstName());
			data.add(user.getLastName());
			data.add(user.getMiddleName());
			data.add(Integer.toString(user.getCellPhone()));
			data.add(user.getAddress());
			data.add(user.getEmail());
			data.add(Boolean.toString(user.isActive()));
			data.add(Integer.toString(user.getOfficePhone()));

			csvPrinter.printRecord(data);
		}
		csvPrinter.close();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(outSteam.toByteArray());
		return new InputStreamResource(inputStream);
	}

}
