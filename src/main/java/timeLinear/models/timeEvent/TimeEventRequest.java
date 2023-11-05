package timeLinear.models.timeEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timeLinear.models.category.CategoryResponse;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventRequest {
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private String iconType = null;
    private String iconSource = null;
    private CategoryResponse category = null;
}
