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

//    private int age_range;
//
//    private String gender;

    @OneToMany(mappedBy = "user")
    private List<UserKeyword> keyword = new ArrayList<>();

}
