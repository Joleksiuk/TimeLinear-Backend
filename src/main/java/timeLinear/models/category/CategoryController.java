package timeLinear.models.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import timeLinear.models.user.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private  CategoryRepository categoryRepository;
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest data) {
        try{
            Category category = categoryRepository.save(new Category(data));
            categoryService.assignUserToCategory(category);
            return ResponseEntity.ok().body(new CategoryResponse(category));
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try{
            Optional<Category> timeEvent = categoryRepository.findById(categoryId);
            timeEvent.ifPresent(event -> categoryRepository.delete(event));
            return ResponseEntity.ok().body("Category deleted!");
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest data) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Category updatedCategory = new Category(data);
            updatedCategory.setId(categoryId);
            categoryRepository.save(updatedCategory);
            return ResponseEntity.ok().body("Category updated!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/owned")
    public ResponseEntity<CategoryBulkResponse> getOwnedCategories() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user !=null ){
            List<Category> categories = categoryRepository.findAllByOwner(user);
            List<CategoryResponse> categoryResponses = categories.stream().map(CategoryResponse::new).toList();
            return ResponseEntity.ok().body(new CategoryBulkResponse(categoryResponses));
        }
        return ResponseEntity.notFound().build();
    }
}
