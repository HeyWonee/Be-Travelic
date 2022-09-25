package beTravelic.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="user_categories")
public class UserCategories {

    @Id @GeneratedValue
    @Column(name = "user_categories_id")
    private Long user_categories_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    @Builder
    public UserCategories(User user, Categories categories){
        this.categories = categories;
        this.user = user;
    }


}
