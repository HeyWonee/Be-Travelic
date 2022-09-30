package beTravelic.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Survey {
    @Id @GeneratedValue
    @Column(name = "survey_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ElementCollection
    @Column(name="surveykeyword")
    private List<String> surveykeyword;

    @ElementCollection
    @Column(name="surveycategory")
    private List<String> surveycategory;

    @Builder
    public Survey(User user, List<String> survey_keyword, List survey_category){
        this.surveykeyword = survey_keyword;
        this.surveycategory = survey_category;
        this.user = user;
    }
}
