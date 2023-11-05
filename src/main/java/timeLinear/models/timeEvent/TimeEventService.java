package timeLinear.models.timeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import timeLinear.models.category.Category;
import timeLinear.models.category.CategoryRepository;
import timeLinear.models.category.CategoryResponse;
import timeLinear.models.user.User;

@Service
public class TimeEventService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void assignUserToTimeEvent(TimeEvent timeEvent) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        timeEvent.setOwner(currentUser);
    }
    public void assignCategoryToTimeEvent(CategoryResponse category, TimeEvent timeEvent) {
        if(category==null){
            timeEvent.setCategory(null);
        }
        else{
            Category categoryValue = categoryRepository.findById(category.getId()).orElse(null);
            timeEvent.setCategory(categoryValue);
        }
    }
}
