package beTravelic.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserKeyword {
    @Id @GeneratedValue
    @Column(name = "user_keyword_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "keyword_id")
    private Keywords keywords;
}
