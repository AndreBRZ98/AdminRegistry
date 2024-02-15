package com.nelr.adminregistry.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.nelr.adminregistry.entity.Login;
import com.nelr.adminregistry.repository.LoginRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Login> loginOptional = loginRepository.findByCorreo(username);
		Login login = loginOptional.orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado: " + username));
		
		UserDetails userDetails = User
                .withUsername(login.getUsername())
                .password(login.getPassword())
                .authorities(login.getAuthorities())
                .accountExpired(!login.isAccountNonExpired())
                .accountLocked(!login.isAccountNonLocked())
                .credentialsExpired(!login.isCredentialsNonExpired())
                .disabled(!login.isEnabled())
                .build();
		return userDetails;
	}

}
