package beTravelic.demo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//  마이페이지 여행 기록
@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long review_id;

    //    여행지
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    //    유저
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //    게시글 내용
    @Column(name = "contents")
    private String contents;

    //    게시글 이미지
    @Column(name = "image")
    private String image;

    //    평점
    @Column(name = "score")
    private Long score;

    //    게시글 작성일
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    //    여행지 방문일
    @Column(name = "visited_at")
    private String visited_at;

    //    Comment와 연결
    @OneToMany(mappedBy = "review")
    private List<Comment> comments = new ArrayList<>();

}
