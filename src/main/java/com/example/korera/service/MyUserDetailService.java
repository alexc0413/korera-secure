package com.example.korera.service;

import com.example.korera.repository.UserRep;
import com.example.korera.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
/*2. MyCustomUserService implements the default UserDetailsService of the framework.
Users can be obtained from the database according to their username to check
whether the user exists*/
@Service
public class MyUserDetailService implements UserDetailsService {
    /*** During login verification, obtain all the user's authority information
     * through username* And return UserDetails to spring's global cache SecurityContextHolder
     * for use by the authorizer*/
    @Autowired
    private UserRep userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}