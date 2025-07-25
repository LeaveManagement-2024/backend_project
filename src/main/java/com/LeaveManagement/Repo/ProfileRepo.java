package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ProfileRepo extends JpaRepository<Profiles,Long> {
    Optional<Profiles> findByProfileName(String profileName);
}
