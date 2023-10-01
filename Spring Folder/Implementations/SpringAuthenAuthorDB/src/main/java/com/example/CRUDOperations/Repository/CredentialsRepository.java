package com.example.CRUDOperations.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRUDOperations.Dto.CredentialsDto;
import com.example.CRUDOperations.Entity.Credentials;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

	Credentials save(Credentials credentials);

    Optional<Credentials> findByUsername(String username);

}
