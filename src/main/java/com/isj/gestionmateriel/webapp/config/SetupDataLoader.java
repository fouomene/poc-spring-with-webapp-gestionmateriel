package com.isj.gestionmateriel.webapp.config;

import com.isj.gestionmateriel.webapp.model.entities.Role;
import com.isj.gestionmateriel.webapp.model.entities.User;
import com.isj.gestionmateriel.webapp.repository.RoleRepository;
import com.isj.gestionmateriel.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
 
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
        if (alreadySetup)
            return;

        createRoleIfNotFound("ADMIN");

        Role adminRole = roleRepository.findByRole("ADMIN");
        User user = new User();
        user.setName("Test");
        user.setLastName("Test");
        user.setPassword(bCryptPasswordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setActive(1);
        user.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));

        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
 
        Role role = roleRepository.findByRole(name);
        if (role == null) {
            role = new Role();
            role.setRole(name);
            roleRepository.save(role);
        }
        return role;
    }
}