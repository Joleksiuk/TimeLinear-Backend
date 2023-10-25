package timeLinear.models.userGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import timeLinear.models.user.User;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByOwner(User user);
}
