package beTravelic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Keywords {
    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;

    // One to one 관계 정의를 id에 해야하는지 자동으로 설정되는지?
    // 카테고리랑 관계도 애매
    private String name;
}
