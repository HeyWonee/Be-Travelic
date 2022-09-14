package beTravelic.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Follow {

    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    // user 클래스에 onetomany 속성값은 어떻게 처리?
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long user_id;

    private String following_id;

    private String follower_id;

}
