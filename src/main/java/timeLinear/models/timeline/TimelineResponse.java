package timeLinear.models.timeline;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimelineResponse {
    private Long id;
    private String name;
    private String description;
    private String creationDate;

    public TimelineResponse (Timeline timeline){
        this.id = timeline.getId();
        this.name = timeline.getName();
        this.description = timeline.getDescription();
        this.creationDate = timeline.getCreationDate();
    }
}
