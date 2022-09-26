package beTravelic.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @Column(name = "pw", nullable = false)
    private String pw;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "email")
    private String email;
    private String refreshToken;

    @OneToMany(mappedBy = "user")
    private List<UserCategories> userCategories = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @Setter
    private List<Survey> surveys = new ArrayList<>();
    @Setter
    @OneToMany
    private List<Follow> following = new ArrayList<>();

    @Setter
    @OneToMany
    private List<Follow> follower = new ArrayList<>();



    @Setter
    @OneToMany(mappedBy = "user")
    private List<UserPlace> userPlaces = new ArrayList<>();
//
    @Setter
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "user")
    private List<ReviewLike> reviewLikes = new ArrayList<>();




    @Setter
    @OneToMany(mappedBy = "user")
    private List<UserKeywords> userKeywords = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "user")
    private List<MypagePicture> mypagePictures = new ArrayList<>();

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    @Builder
    public User(String pw, String id, String nickname, String email){
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.email = email;
    }

    @Builder(builderClassName = "ReviewUserId", builderMethodName = "ReviewUserId")
    public User(Long user_id) {
        this.userId = user_id;
    }

}
