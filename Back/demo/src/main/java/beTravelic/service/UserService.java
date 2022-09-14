//package beTravelic.service;
//
//import beTravelic.domain.User;
//import beTravelic.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//
//@Service
//@Transactional(readOnly = true)
//
//public class UserService {
//
//    private UserRepository userRepository;
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    // 회원 가입
//    @Transactional
//    public Long join(User user) {
//        // 중복 회원 체크
//        validateDuplidateUser(user);
//        userRepository.save(user);
//        return user.getUserId();
//    }
//
//    public void validateDuplidateUser(User user) {
//        List<User> findUsers = userRepository.findByNickname(user.getNickname());
//        if (!findUsers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
//    // 회원 전체 조회
//    public List<User> findUsers() {
//        return userRepository.findAll();
//    }
//
//    public User findOne(Long userId) {
//        return userRepository.findOne(userId);
//    }
//}
