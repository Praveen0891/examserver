package com.exam.controller;

import com.exam.config.JwtUtils;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private com.exam.service.impl.UserDetailsServiceImpl userDetailServiceImpl;

    @Autowired
    private JwtUtils jwtutils;
    private UserDetailsService userDetailService;


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try
        {

            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());


        }
        catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("User Not found");
        }

        UserDetails userDetails=this.userDetailServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtutils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }

    private  void  authenticate(String username,String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));


        }
        catch (DisabledException e)
        {
            throw  new Exception("User disabled");
        }
        catch (BadCredentialsException e)
        {
            throw  new Exception("Invalid Credentials"+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public UserDetails getCurrentUser(Principal principal)
    {
   return this.userDetailService.loadUserByUsername(principal.getName());
    }

}



//===========================================================================
//package com.exam.controller;
//
//import com.exam.config.JwtUtils;
//import com.exam.helper.UserNotFoundException;
//import com.exam.model.JwtRequest;
//import com.exam.model.JwtResponse;
//import com.exam.model.User;
//import com.exam.service.impl.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//
//@RestController
//@CrossOrigin(
//        origins = {
//                "http://localhost:4200"
//        },
//        methods = {
//                RequestMethod.OPTIONS,
//                RequestMethod.GET,
//                RequestMethod.PUT,
//                RequestMethod.DELETE,
//                RequestMethod.POST
//        })
//public class AuthenticateController {
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//
//    //generate token
//
//    @PostMapping("/generate-token")
//    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//
//        try {
//
//            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
//
//
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//            throw new Exception("User not found ");
//        }
//
//        /////////////authenticate
//
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
//        String token = this.jwtUtils.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//
//
//    }
//
//
//    private void authenticate(String username, String password) throws Exception {
//
//        try {
//
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        } catch (DisabledException e) {
//            throw new Exception("USER DISABLED " + e.getMessage());
//        } catch (BadCredentialsException e) {
//            throw new Exception("Invalid Credentials " + e.getMessage());
//        }
//    }
//
//    //return the details of current user
//    @GetMapping("/current-user")
//    public User getCurrentUser(Principal principal) {
//        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
//
//    }
//
//
//
//}
