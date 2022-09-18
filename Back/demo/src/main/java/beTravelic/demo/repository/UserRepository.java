package beTravelic.demo.repository;

import beTravelic.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CRUD 함수를 JpaRepository가 들고있음

public interface UserRepository extends JpaRepository<User, Integer> {

//    Optional<Object> findByUserEmail(String kakaoEmail);
}
