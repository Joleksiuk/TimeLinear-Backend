package timeLinear.models.timeEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/timeEvents")
@RequiredArgsConstructor
public class TimeEventController {

    @Autowired
    private TimeEventRepository timeEventRepository;

    @Autowired TimeEventService timeEventService;

    @PostMapping
    public ResponseEntity<TimeEventResponse> createTimeEvent(@RequestBody TimeEventRequest data) {
        try{
            TimeEvent timeEvent = timeEventRepository.save(new TimeEvent(data));
            timeEventService.assignUserToTimeEvent(timeEvent);
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
            timeEventRepository.save(updatedTimeEvent);
            return ResponseEntity.ok().body("Time Event updated!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
