package beTravelic.demo.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Keywords {

    @Id @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;

    private String name;
}
