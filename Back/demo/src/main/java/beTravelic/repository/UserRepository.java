//package beTravelic.repository;
//
//
//import beTravelic.domain.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class UserRepository {
//
//    public final EntityManager em;
//
//    public void save(User user) {
//        em.persist(user);
//    }
//
//    public User findOne(Long userId) {
//        return em.find(User.class, userId);
//    }
//
//    public List<User> findAll() {
//        List<User> result = em.createQuery("select u from User u", User.class)
//                .getResultList();
//        return result;
//    }
//
//    public List<User> findByNickname(String nickname) {
//        return em.createQuery("select u from User u where u.nickname = :nickname", User.class)
//                .setParameter("nickname", nickname)
//                .getResultList();
//    }
//
//}
