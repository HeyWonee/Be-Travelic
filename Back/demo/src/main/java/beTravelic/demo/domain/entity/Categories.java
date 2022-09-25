package beTravelic.demo.domain.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Categories {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long category_id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "category_type_id")
    private Integer category_type_id;
}
