package timeLinear.models.timeline;

import org.springframework.data.jpa.repository.JpaRepository;
import timeLinear.models.user.User;

import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    List<Timeline> findAllByOwner(User owner);

}
