package beTravelic.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Categories {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    private int type_id;

}
