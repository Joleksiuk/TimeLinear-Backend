package timeLinear.models.timelineTimeEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class TimelineTimeEventBean {
    private Long timelineId;
    private Long timeEventId;
}
