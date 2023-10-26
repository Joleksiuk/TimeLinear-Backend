package timeLinear.models.timeline;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import timeLinear.models.user.User;

@Service
public class TimelineService {

    public void assignUserToTimeline(Timeline timeline) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        timeline.setOwner(currentUser);
    }
}
