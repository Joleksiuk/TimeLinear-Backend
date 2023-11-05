package timeLinear.models.category;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import timeLinear.models.user.User;

@Entity
@Data
@Table(name = "_CATEGORY")
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COLOR")
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;


    public Category (CategoryRequest categoryRequest) {
        this.name = categoryRequest.getName();
        this.color = categoryRequest.getColor();
        this.owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
