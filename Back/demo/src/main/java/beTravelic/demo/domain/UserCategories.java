package beTravelic.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserCategories {
    @Id @GeneratedValue
    @Column(name = "user_categories_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Categories categories;
}
