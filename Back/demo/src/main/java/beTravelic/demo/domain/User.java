package beTravelic.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String email;

    private String nickname;

    private String age_range;

    private String gender;
    private String pw;

    @OneToMany(mappedBy = "user")
    private List<UserKeyword> keyword = new ArrayList<>();

    @Builder
    public User(Long id, String email, String gender, String age_range, String pw){
        this.pw = pw;
        this.email = email;
        this.gender = gender;
        this.age_range = age_range;
    }
}
