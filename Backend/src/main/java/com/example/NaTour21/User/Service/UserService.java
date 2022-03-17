package com.example.NaTour21.User.Service;

import com.example.NaTour21.User.Entity.User;
import com.example.NaTour21.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findById(username).get();
        if(user == null)
        {
            throw new UsernameNotFoundException("Nome utente non registrato");
        }
        Collection<SimpleGrantedAuthority> authotity = new ArrayList<>();
        authotity.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authotity);
    }

    public User saveUser(User user) throws Exception {
        if (!userRepository.existsById(user.getUsername())) {
            if(userRepository.findByEmail(user.getEmail()) == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            }else
            {
                throw new Exception("Email già registrata");
            }
        } else {
            throw new Exception("Nome utente non disponibile");
        }
    }

    public User getUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if(!user.isPresent())
        {
            return null;
        }
        return user.get();
    }

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateEmail(User user) {
        userRepository.save(user);
    }

    public User getUserByFacebookId(String id) {
        User user = userRepository.findByFacebookId(id);
        return user;
    }
}