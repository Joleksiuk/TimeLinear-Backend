package timeLinear.models.timeEvent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import timeLinear.models.user.User;

@Service
public class TimeEventService {

    public void assignUserToTimeEvent(TimeEvent timeEvent) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        timeEvent.setOwner(currentUser);
    }
}
