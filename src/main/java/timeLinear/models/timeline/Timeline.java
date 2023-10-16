package timeLinear.models.timeline;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import timeLinear.models.timeEvent.TimeEvent;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "TIMEVENT")
@NoArgsConstructor
public class Timeline {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATIONDATE")
    private String creationDate;

    @OneToMany(mappedBy = "timeline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeEvent> timeEvents = new ArrayList<>();

    public Timeline (TimelineBean timelineBean) {
        this.name = timelineBean.getName();
        this.description = timelineBean.getDescription();
        this.creationDate = timelineBean.getCreationDate();
    }

    public void addEvent(TimeEvent timeEvent){
        timeEvents.add(timeEvent);
    }

    public void removeEvent(TimeEvent timeEvent){
        timeEvents.remove(timeEvent);
    }
}
