package com.cydeo.service.Impl;

import com.cydeo.entity.User;
import com.cydeo.entity.common.UserPrincipal;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImp implements SecurityService {


    private final UserRepository userRepository;


    public SecurityServiceImp(AccountRepository accountRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // we need to get our own user from database
        User user = userRepository.findByUsername(username)
                // this is the second way using optional class
                .orElseThrow(()->new UsernameNotFoundException("this user is not found"));
        // return some exception if user doesn't exist
//        if(user == null){
//            throw new UsernameNotFoundException("this user is not found");
//        }
        // return user information as a UserDetails
        return new UserPrincipal(user);
    }
}
