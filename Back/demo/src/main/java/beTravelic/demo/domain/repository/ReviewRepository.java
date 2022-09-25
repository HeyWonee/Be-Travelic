package beTravelic.demo.domain.repository;


import beTravelic.demo.domain.entity.Region;
import beTravelic.demo.domain.entity.Review;
import beTravelic.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findReviewByReview_id(long ReviewId);
//    @Query("SELECT r FROM Review r WHERE r.user=: user AND r.place.region=:regionId ")
//    List<ReviewResDto> findAllByUserAndRegion(User user, Region regionId);
}
