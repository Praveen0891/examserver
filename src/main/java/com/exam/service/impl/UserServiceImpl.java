package com.exam.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private com.exam.repo.UserRepository userRepository;

    @Autowired
    private com.exam.repo.RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local =this.userRepository.findByUsername(user.getUsername());

        if(local !=null)
        {
            System.out.println("User already present");
            throw  new Exception("User already present");
        }
        else
        {
            for(UserRole ur:userRoles)
            {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local=this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String uname) {
        return this.userRepository.findByUsername(uname);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

}




//====================================================================
//package com.exam.service.impl;
//
//import com.exam.helper.UserFoundException;
//import com.exam.helper.UserNotFoundException;
//import com.exam.model.Role;
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.repo.RoleRepository;
//import com.exam.repo.UserRepository;
//import com.exam.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    //creating user
//    @Override
//    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
//
//
//        User local = this.userRepository.findByUsername(user.getUsername());
//        if (local != null) {
//            System.out.println("User is already there !!");
//            throw new UserFoundException();
//        } else {
//            //user create
//            for (UserRole ur : userRoles) {
//                roleRepository.save(ur.getRole());
//            }
//
//            user.getUserRoles().addAll(userRoles);
//            local = this.userRepository.save(user);
//
//        }
//
//        return local;
//    }
//
//    //getting user by username
//    @Override
//    public User getUser(String username) {
//        return this.userRepository.findByUsername(username);
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        this.userRepository.deleteById(userId);
//    }
//
//
//}
