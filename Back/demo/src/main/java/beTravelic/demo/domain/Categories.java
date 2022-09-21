package beTravelic.demo.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Categories {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String category_name;

    private String content_type_id;

}
