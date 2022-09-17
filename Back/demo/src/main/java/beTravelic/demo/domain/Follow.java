package beTravelic.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Follow {
    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String following_id;

    private String follower_id;
}
