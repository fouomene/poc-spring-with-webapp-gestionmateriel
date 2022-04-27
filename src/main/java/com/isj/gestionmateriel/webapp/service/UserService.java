package com.isj.gestionmateriel.webapp.service;


import java.util.Arrays;
import java.util.HashSet;

import com.isj.gestionmateriel.webapp.model.entities.Role;
import com.isj.gestionmateriel.webapp.model.entities.User;
import com.isj.gestionmateriel.webapp.repository.RoleRepository;
import com.isj.gestionmateriel.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;




@Service("userService")
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UserRepository userRepository,
			RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);

		final Role userRole = roleRepository.findByRole("ADMIN");

		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));


		return userRepository.save(user);
	}

}