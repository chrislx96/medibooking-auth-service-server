package com.medibooking.authenticationserver.repositories;

import com.medibooking.authenticationserver.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
