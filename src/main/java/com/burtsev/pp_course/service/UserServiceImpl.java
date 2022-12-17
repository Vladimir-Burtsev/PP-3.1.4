package com.burtsev.pp_course.service;

import com.burtsev.pp_course.model.Role;
import com.burtsev.pp_course.model.User;
import com.burtsev.pp_course.repositories.RoleRepository;
import com.burtsev.pp_course.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initFirstAdminRole(){

    }
////    @PostConstruct
//    public void initDataUsers(){
////        Role roleAdmin = new Role();
////        roleAdmin.setRolename("ROLE_ADMIN");
////        roleRepository.save(roleAdmin);
//
//        Role adminRole = roleRepository.findById(2).get();
//        User user2 = new User();
//            user2.setRoles(List.of(userRole));
//            user2.setUsername("user2");
//            user2.setPassword(bCryptPasswordEncoder.encode("100"));
//            userRepository.save(user2);
//        User admin1 = new User();
//            admin1.setRoles(List.of(adminRole));
//            admin1.setUsername("admin1");
//            admin1.setPassword(bCryptPasswordEncoder.encode("100"));
//            userRepository.save(admin1);
//        User admin2 = new User();
//            admin2.setRoles(List.of(adminRole));
//            admin2.setUsername("admin2");
//            admin2.setPassword(bCryptPasswordEncoder.encode("100"));
//            userRepository.save(admin2);
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User dbUser = user.get();
        Hibernate.initialize(dbUser.getRoles());
        return dbUser;
    }
    public User findUserById(int userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }
    public List<User> allUsers() {
        return userRepository.findAll();
    }

//    public boolean saveUser(User user) {
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }

    public boolean deleteUser(int userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findById(1).get();
        userRepository.save(user);
    }

    @Transactional

    @Override
    @PostMapping
    public void update(User updatedUser) {
        userRepository.save(updatedUser);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
