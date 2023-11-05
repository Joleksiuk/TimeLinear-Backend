package timeLinear.models.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String color;

    public CategoryResponse(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.color = category.getColor();
    }
}
