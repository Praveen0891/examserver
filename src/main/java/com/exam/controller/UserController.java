//package com.exam.controller;
//
//import com.exam.model.Role;
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.repo.UserRepository;
//import com.exam.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin("*")
//public class UserController {
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private UserService userService;
//
//    private com.exam.repo.UserRepository userRepository;
//    @PostMapping("/")
//    public User createUser(@RequestBody User user) throws Exception {
//        user.setProfile("default.png");
//
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//
//        Role role = new Role();
//        role.setRoleName("NORMAL");
//        Set<UserRole> userRoleSet=new HashSet<>();
//        UserRole userRole=new UserRole();
//        userRole.setRole(role);
//        userRole.setUser(user);
//        userRoleSet.add(userRole);
//        return  this.userService.createUser(user,userRoleSet);
//    }
//
//    @GetMapping("/{userName}")
//    public User getUser(@PathVariable("userName") String uname)
//    {
//        System.out.println(uname);
//
//        return this.userService.getUser(uname);
//    }
//
//    @DeleteMapping("/{userId}")
//    public void deleteUser(@PathVariable("userId") Long uid)
//    {
//        System.out.println(uid);
//        this.userService.deleteUser(uid);
//    }
//
//}
//
//
//
//
//
////===================================================================================================
package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {


        user.setProfile("default.png");
        //encoding password with bcryptpasswordencoder

             user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));


        Role role = new Role();
        role.setRoleName("NORMAL");
        Set<UserRole> userRoleSet=new HashSet<>();
        UserRole userRole=new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoleSet.add(userRole);
        return  this.userService.createUser(user,userRoleSet);
    }
//    Set<UserRole> roles = new HashSet<>();
////
////        Role role = new Role();
////        role.setRoleId(45L);
////        role.setRoleName("NORMAL");
////
////        UserRole userRole = new UserRole();
////        userRole.setUser(user);
////        userRole.setRole(role);
////
////        roles.add(userRole);
//
//
//        return  this.userService.createUser(user,userRoleSet);
//    }




    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    //delete the user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }


    //update api
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }


}
