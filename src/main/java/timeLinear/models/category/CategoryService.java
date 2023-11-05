package timeLinear.models.category;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import timeLinear.models.user.User;

@Service
public class CategoryService {

    public void assignUserToCategory(Category category) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        category.setOwner(currentUser);
    }
}
