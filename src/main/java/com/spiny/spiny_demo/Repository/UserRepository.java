package com.spiny.spiny_demo.Repository;

import com.spiny.spiny_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
  //  User findByUsername(String username);
    Optional<User> findByPhone(String phone);

    List<User> email(String email);
}
