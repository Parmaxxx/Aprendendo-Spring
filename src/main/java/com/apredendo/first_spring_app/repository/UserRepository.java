package com.apredendo.first_spring_app.repository;

import com.apredendo.first_spring_app.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<ModelUser, Long> {

    Optional<ModelUser> findByEmail(String email);
}
