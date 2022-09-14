package beTravelic.domain;

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
    private String id;

    private String pw;

    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<UserKeyword> keyword = new ArrayList<>();

}
