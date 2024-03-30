package timeLinear.models.timeEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import timeLinear.models.timelineTimeEvent.TimelineTimeEvent;
import timeLinear.models.timelineTimeEvent.TimelineTimeEventRepository;
import timeLinear.models.user.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/timeEvents")
@RequiredArgsConstructor
public class TimeEventController {

    @Autowired
    private TimeEventRepository timeEventRepository;

    @Autowired
    private TimeEventService timeEventService;

    @Autowired
    private TimelineTimeEventRepository timelineTimeEventRepository;

    @PostMapping
    public ResponseEntity<TimeEventResponse> createTimeEvent(@RequestBody TimeEventRequest data) {
        try{
            TimeEvent timeEvent = timeEventRepository.save(new TimeEvent(data));
            timeEventService.assignCategoryToTimeEvent(data.getCategory(), timeEvent);
            timeEventService.assignUserToTimeEvent(timeEvent);
            timeEventRepository.save(timeEvent);
            return ResponseEntity.ok().body(new TimeEventResponse(timeEvent));
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteTimeEvent(@PathVariable Long eventId) {
        try{
            Optional<TimeEvent> timeEvent = timeEventRepository.findById(eventId);
            List<TimelineTimeEvent> timelineTimeEvents =
                    timelineTimeEventRepository.findAllByTimeEvent(timeEvent.get());
            timelineTimeEventRepository.deleteAll(timelineTimeEvents);

            timeEvent.ifPresent(event -> timeEventRepository.delete(event));
            return ResponseEntity.ok().body("Time Event deleted!");
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<String> updateTimeEvent(@PathVariable Long eventId, @RequestBody TimeEventRequest timeEventBean) {
        Optional<TimeEvent> timeEvent = timeEventRepository.findById(eventId);
        if (timeEvent.isPresent()) {
            TimeEvent updatedTimeEvent = new TimeEvent(timeEventBean);
            updatedTimeEvent.setId(eventId);
            timeEventService.assignCategoryToTimeEvent(timeEventBean.getCategory(), updatedTimeEvent);
            timeEventRepository.save(updatedTimeEvent);
            return ResponseEntity.ok().body("Time Event updated!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bulk")
    public ResponseEntity<TimeEventsBulkResponse> getEventsInBulk(@RequestBody TimeEventsBulkRequest data) {
        List<TimeEvent> timeEvents = timeEventRepository.findAllById(data.getTimeEventsIds());
        List<TimeEventResponse> timeEventsResponse = timeEvents.stream().map(TimeEventResponse::new).toList();
        return ResponseEntity.ok().body(new TimeEventsBulkResponse(timeEventsResponse));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<TimeEventResponse> getEvent(@PathVariable Long eventId) {
        Optional<TimeEvent> timeEventOptional = timeEventRepository.findById(eventId);
        return timeEventOptional.map(timeEvent -> ResponseEntity.ok().body(new TimeEventResponse(timeEvent)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/owned")
    public ResponseEntity<TimeEventsBulkResponse> getOwnedEvents() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user !=null ){
            List<TimeEvent> timeEvents = timeEventRepository.findAllByOwner(user);
            List<TimeEventResponse> timeEventsResponse = timeEvents.stream().map(TimeEventResponse::new).toList();
            return ResponseEntity.ok().body(new TimeEventsBulkResponse(timeEventsResponse));
        }
        return ResponseEntity.notFound().build();
    }
}
