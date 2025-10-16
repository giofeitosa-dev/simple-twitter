package br.com.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.entities.User;

@Repository
public interface UserRepository extends JpaRepository <User, UUID>{

}
