package timeLinear.models.category;

import org.springframework.data.jpa.repository.JpaRepository;
import timeLinear.models.timeEvent.TimeEvent;
import timeLinear.models.user.User;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByName(String name);
    List<Category> findAllByColor(String color);
    List<Category> findAllByOwner(User owner);


}
