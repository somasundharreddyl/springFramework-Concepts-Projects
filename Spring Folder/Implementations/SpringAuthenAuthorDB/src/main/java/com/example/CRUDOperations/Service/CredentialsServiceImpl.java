package com.example.CRUDOperations.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CRUDOperations.Config.UserDetailsImpl;
import com.example.CRUDOperations.Dto.CredentialsDto;
import com.example.CRUDOperations.Entity.Credentials;
import com.example.CRUDOperations.Repository.CredentialsRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsServiceImpl implements CredentialsService,UserDetailsService {

	@Autowired
	private CredentialsRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
//	public CredentialsServiceImpl(CredentialsRepository repo) {
//		super();
//		this.repo = repo;
//	}

	@Override
	public Credentials register(Credentials credentials) {
		credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
		return repo.save(credentials);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<Credentials> credentials =	repo.findByUsername(username);
	
		return credentials.map(UserDetailsImpl::new)
				          .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
	}

}
