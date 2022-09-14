package beTravelic.service;

import beTravelic.domain.User;
import beTravelic.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Test
    public void 회원가입() throws Exception {
        // given
        User user = new User();
        user.setId("kim");

        // when
        Long saveId = userService.join(user);

        // then
        assertEquals(user, userRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원() throws Exception {
        // given

        // when

        // then
    }
}