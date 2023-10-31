package timeLinear.models.timeline;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import timeLinear.models.timeEvent.TimeEvent;
import timeLinear.models.timeEvent.TimeEventRepository;
import timeLinear.models.user.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/timelines")
@RequiredArgsConstructor
public class TimelineController {

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private TimeEventRepository timeEventRepository;

    @Autowired TimelineService timelineService;

    @PostMapping
    public ResponseEntity<TimelineResponse> createTimeline(@RequestBody TimelineRequest timelineBean) {
        try{
            Timeline timeline = timelineRepository.save(new Timeline(timelineBean));
            return ResponseEntity.ok().body(new TimelineResponse(timeline));
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTimeline(@PathVariable Long id, @RequestBody TimelineRequest timelineBean) {
        try {
            Optional<Timeline> timelineOptional = timelineRepository.findById(id);
            if (timelineOptional.isPresent()) {
                Timeline updatedTimeline = new Timeline(timelineBean);
                updatedTimeline.setId(id);
                timelineRepository.save(updatedTimeline);
                return ResponseEntity.ok().body("Timeline updated!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update Timeline: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimelineResponse> getTimeline(@PathVariable Long id) {
        Optional<Timeline> timelineOptional = timelineRepository.findById(id);
        return timelineOptional.map(timeline -> ResponseEntity.ok().body(new TimelineResponse(timeline)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bulk")
    public ResponseEntity<TimelinesBulkResponse> getTimelinesInBulk(@RequestBody TimelinesBulkRequest data) {
        List<Timeline> timelines = timelineRepository.findAllById(data.getTimelineIds());
        List<TimelineResponse> timelineResponses = timelines.stream().map(TimelineResponse::new).toList();
        return ResponseEntity.ok().body(new TimelinesBulkResponse(timelineResponses));
    }

    @GetMapping("/owned")
    public ResponseEntity<TimelinesBulkResponse> getOwnedEvents() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user !=null ){
            List<Timeline> timelines = timelineRepository.findAllByOwner(user);
            List<TimelineResponse> timelineResponses = timelines.stream().map(TimelineResponse::new).toList();
            return ResponseEntity.ok().body(new TimelinesBulkResponse(timelineResponses));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTimeline(@PathVariable Long id) {
        try {
            Optional<Timeline> timelineOptional = timelineRepository.findById(id);
            if (timelineOptional.isPresent()) {
                timelineRepository.delete(timelineOptional.get());
                return ResponseEntity.ok().body("Timeline deleted!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete Timeline: " + e.getMessage());
        }
    }

    @PostMapping("/events")
    public ResponseEntity<String> addEventToTimeline(@RequestBody TimelineTimeEventBean data) {
        try {

            Optional<Timeline> timelineOptional = timelineRepository.findById(data.getTimelineId());
            if (timelineOptional.isPresent()) {
                Optional<TimeEvent> timeEventOptional = timeEventRepository.findById(data.getTimeEventId());
                timeEventOptional.ifPresent(timeEvent -> timelineOptional.get().addEvent(timeEvent));
                timelineRepository.save(timelineOptional.get());
                return ResponseEntity.ok().body("Time Event added to timeline!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add Timeline to Event!" + e.getMessage());
        }
    }

    @DeleteMapping("/events")
    public ResponseEntity<String> deleteEventFromTimeline(@RequestBody TimelineTimeEventBean data) {
        try {
            Optional<Timeline> timelineOptional = timelineRepository.findById(data.getTimelineId());
            if (timelineOptional.isPresent()) {
                Optional<TimeEvent> timeEventOptional = timeEventRepository.findById(data.getTimeEventId());
                timeEventOptional.ifPresent(timeEvent -> timelineOptional.get().removeEvent(timeEvent));
                timelineRepository.save(timelineOptional.get());
                return ResponseEntity.ok().body("Time Event removed from timeline!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove Event from Timeline!" + e.getMessage());
        }
    }
}
