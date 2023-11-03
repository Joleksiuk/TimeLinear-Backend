package timeLinear.models.timeline;

import org.springframework.data.jpa.repository.JpaRepository;
import timeLinear.models.user.User;
import timeLinear.models.userGroup.Group;

import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    List<Timeline> findAllByOwner(User owner);

    List<Timeline> findAllByGroupIn(List<Group> groups);


}
