package com.newenergycodes.demojpadatabase;

import com.newenergycodes.demojpadatabase.domain.User;
import com.newenergycodes.demojpadatabase.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
//@Component
//@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loaduserbyUsername: {}", username);

        log.debug("passwordencoded: {}", passwordEncoder.encode("test"));

        log.debug("does pw match? {}, {}, {}", "test", "$2a$10$eDM4ubOYYf9o3YQvjls3Befg0u1ksWXo2UTOgr/lpEQ2r3HSl2hMm",
                passwordEncoder.matches("test", "$2a$10$eDM4ubOYYf9o3YQvjls3Befg0u1ksWXo2UTOgr/lpEQ2r3HSl2hMm" ));

        log.debug("does pw match? {}, {}, {}", "test", "$2a$10$SMMi7k.XzDfjDQdtG2QfGeMUW2DEL9mv1wHwknHOSNHbOl4L3l3Qa",
                passwordEncoder.matches("test", "$2a$10$SMMi7k.XzDfjDQdtG2QfGeMUW2DEL9mv1wHwknHOSNHbOl4L3l3Qa" ));

        log.debug("does pw match? {}, {}, {}", "test", "$2a$10$/4fU.TOjcowXA.Qyyih.UuubMOgA2wRohgSiTVu3NR9t4Y/CLXe3i",
                passwordEncoder.matches("test", "$2a$10$/4fU.TOjcowXA.Qyyih.UuubMOgA2wRohgSiTVu3NR9t4Y/CLXe3i" ));

        Optional<User> user = userRepository.findByUsernameIs(username);

        log.debug("loaduserOptional: {}", user);

        if (user.isPresent()){
            return org.springframework.security.core.userdetails.User.withUsername(user.get().getUsername())
                            .password(user.get().getPasswordEncoded())
                            .roles("USER")
                            .build();
        } else {
            throw new UsernameNotFoundException("username" + username + " not found");
        }
       /* return userRepository.findByUsernameIs(username)
                .map(u -> org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                                .username(u.getUsername())
                                .password(u.getPasswordEncoded()) //TODO: !!!
                                .roles("USER")
                                .build()).orElseThrow(() -> new UsernameNotFoundException("username " + username + " not found")); */
    }
}
