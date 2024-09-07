package com.lucas.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.curso.springboot.app.springboot_crud.entities.Role;
import com.lucas.curso.springboot.app.springboot_crud.entities.User;
import com.lucas.curso.springboot.app.springboot_crud.repositories.RoleRepository;
import com.lucas.curso.springboot.app.springboot_crud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepositoy;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();

    }
        

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> optioanlRoleUser = roleRepositoy.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optioanlRoleUser.ifPresent(roles::add);

        if (user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepositoy.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }


    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

}
