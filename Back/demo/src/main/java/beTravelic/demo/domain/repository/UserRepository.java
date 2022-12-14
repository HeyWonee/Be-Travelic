package beTravelic.demo.domain.repository;

import beTravelic.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인 : 사용자 아이디 비번 일치 확인
    Optional<User> findUserByIdAndPw(String id, String pw);
    // 사용자 정보 조회
    Optional<User> findUserById(String id);
//    Optional<User> findByUser_id(Long user_id);
    
//    Optional<User> findUserByUser_id(long user_id);
    // 닉네임 중복 확인
    boolean existsUserByNickname(String nickname);
    boolean existsUserByEmail(String email);
    Optional<User> findUserByRefreshToken(String refreshToken);

    @Query("SELECT u FROM User u WHERE u.user_id=:userId")
    Optional<User> findUserByUserId(@Param("userId") Long userId);

//    User findByUser_id(Long userId);

//    User findByUserId(Long userId);

//    User findUserByUserId(Long userId);



}
