package com.exam.service.impl;

import com.exam.model.User;
import com.exam.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private com.exam.repo.UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=this.userRepository.findByUsername(s);
        if(user==null)
        {
            System.out.println("User not found exception");
            throw new UsernameNotFoundException("No user found");
        }
        return user;
    }
}




//============================================================
//package com.exam.service.impl;
//
//import com.exam.model.User;
//import com.exam.repo.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = this.userRepository.findByUsername(username);
//        if (user == null) {
//            System.out.println("User not found");
//            throw new UsernameNotFoundException("No user found !!");
//        }
//
//        return user;
//    }
//}
