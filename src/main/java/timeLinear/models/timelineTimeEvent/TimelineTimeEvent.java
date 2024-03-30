package timeLinear.models.timelineTimeEvent;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timeLinear.models.timeEvent.TimeEvent;
import timeLinear.models.timeline.Timeline;

@Entity
@Table(name = "_TIMELINE_TIMEEVENT")
@Getter
@Setter
@NoArgsConstructor
public class TimelineTimeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeline_id", nullable = false)
    private Timeline timeline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeevent_id", nullable = false)
    private TimeEvent timeEvent;

    public TimelineTimeEvent(Timeline timeline, TimeEvent timeEvent) {
        this.timeline = timeline;
        this.timeEvent = timeEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public TimeEvent getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(TimeEvent timeEvent) {
        this.timeEvent = timeEvent;
    }
}