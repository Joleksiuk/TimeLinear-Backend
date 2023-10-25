package timeLinear.models.timeline;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import timeLinear.models.timeEvent.TimeEvent;
import timeLinear.models.user.User;
import timeLinear.models.userGroup.Group;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "TIMELINE")
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

    @ManyToMany
    @JoinTable(
            name = "TIMELINE_TIMEEVENT",
            joinColumns = @JoinColumn(name = "timeline_id"),
            inverseJoinColumns = @JoinColumn(name = "timeevent_id")
    )
    private List<TimeEvent> timeEvents = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group editGroup;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group browseGroup;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Timeline (TimelineRequest timelineBean) {
        this.name = timelineBean.getName();
        this.description = timelineBean.getDescription();
        this.creationDate = timelineBean.getCreationDate();
        this.owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void addEvent(TimeEvent timeEvent){
        timeEvents.add(timeEvent);
    }

    public void removeEvent(TimeEvent timeEvent){
        timeEvents.remove(timeEvent);
    }
}
