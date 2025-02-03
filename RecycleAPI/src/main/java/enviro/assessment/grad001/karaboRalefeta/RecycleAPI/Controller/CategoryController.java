package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.ApiResponse;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service.CategoryService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService cs = new CategoryService();

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getCategories(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), cs.getAll()));
    }
    @GetMapping("/{category}")
    public ResponseEntity<ApiResponse<Object>> getItemsUnderCategory(@PathVariable String category){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), cs.getAllItems(category)));
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addNewCateg(@RequestBody JsonNode body){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), cs.newCategory(new JSONObject(body))));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCategories(@PathVariable("id") long id){
        cs.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.ACCEPTED.value(), "Category has been successfully deleted"));

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<Object>> editCategories(@PathVariable("id") long id, @RequestBody JsonNode body){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.ACCEPTED.value(), cs.editCategory(id, new JSONObject(body.toString()))));
    }
}
