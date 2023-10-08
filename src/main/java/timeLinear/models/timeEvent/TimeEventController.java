package timeLinear.models.timeEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/timeEvent")
@RequiredArgsConstructor
public class TimeEventController {

    @Autowired
    private TimeEventRepository timeEventRepository;

    @PostMapping
    public ResponseEntity<String> createTimeEvent(@RequestBody TimeEventBean timeEventBean) {
        try{
            timeEventRepository.save(new TimeEvent(timeEventBean));
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Failed to create TimeEvent: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Time Event created!");
    }
}
