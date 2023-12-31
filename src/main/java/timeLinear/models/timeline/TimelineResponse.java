package timeLinear.models.timeline;

import lombok.*;
import timeLinear.models.timeEvent.TimeEventResponse;
import timeLinear.models.userGroup.Group;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimelineResponse {
    private Long id;
    private String name;
    private String description;
    private String creationDate;
    private List<TimeEventResponse> timeEvents;

    private Group group;

    private String ownerEmail;

    public TimelineResponse (Timeline timeline){
        this.id = timeline.getId();
        this.name = timeline.getName();
        this.description = timeline.getDescription();
        this.creationDate = timeline.getCreationDate();
        this.timeEvents = timeline.getTimeEvents().stream().map(TimeEventResponse::new).toList();
        this.group = timeline.getGroup();
        this.ownerEmail = timeline.getOwner().getEmail();
    }
}
