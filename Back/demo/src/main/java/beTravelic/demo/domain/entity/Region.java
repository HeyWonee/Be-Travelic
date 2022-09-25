package beTravelic.demo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//  지역 선택
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long region_id;

//    지역코드
    @Column
    private Long area_code;

//    도/광역시
    @Column
    private String do_gwangyuksi;

    @OneToMany(mappedBy = "region")
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "region")
    private List<Review> reviews = new ArrayList<>();

    @Builder(builderClassName = "ReviewRegionId", builderMethodName = "ReviewRegionId")
    public Region(Long region_id) {
        this.region_id = region_id;
    }

}
