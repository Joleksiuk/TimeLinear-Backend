package timeLinear.models.timeEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import timeLinear.models.user.User;

import java.util.List;

public interface TimeEventRepository extends JpaRepository<TimeEvent, Long> {
    List<TimeEvent> findAllByOwner(User owner);
}
