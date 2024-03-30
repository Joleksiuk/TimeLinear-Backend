package timeLinear.models.timelineTimeEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import timeLinear.models.timeEvent.TimeEvent;
import timeLinear.models.timeline.Timeline;

import java.util.List;

public interface TimelineTimeEventRepository extends JpaRepository<TimelineTimeEvent, Long> {

    List<TimelineTimeEvent> findAllByTimeline (Timeline timeline);

    List<TimelineTimeEvent> findAllByTimeEvent (TimeEvent timeEvent);

}
