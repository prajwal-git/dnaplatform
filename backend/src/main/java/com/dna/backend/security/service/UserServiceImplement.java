package com.dna.backend.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dna.backend.modle.Role;
import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);

	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid");
		} else {
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole_name()));
			}
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					authorities);
		}

	}

	@Override
	public User save(UserRepository repository) {
		// TODO Auto-generated method stub

		return null;
	}

}
