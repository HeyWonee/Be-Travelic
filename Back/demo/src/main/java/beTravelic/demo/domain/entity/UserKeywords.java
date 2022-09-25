package beTravelic.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_keywords")
@Getter @Setter
@NoArgsConstructor
public class UserKeywords {

    @Id @GeneratedValue
    @Column(name = "user_keyword_id")
    private Long user_keyword_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "keyword_id")
    private Keywords keywords;

    @Builder
    public UserKeywords(User user, Keywords keywords) {
        this.keywords = keywords;
        this.user = user;
    }
}
