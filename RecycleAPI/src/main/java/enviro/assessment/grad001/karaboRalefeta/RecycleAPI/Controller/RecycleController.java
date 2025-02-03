package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.ApiResponse;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service.ItemService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recycle")
public class RecycleController {
    ItemService is = new ItemService();
    @GetMapping("/item")
    public ResponseEntity<ApiResponse<Object>> recyclingInfo(@RequestParam(required = false) Long id, @RequestParam(required = false) String name){
        if (id == null && (name == null || name.isEmpty())){
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),  is.getRandom()));
        }
        if (name != null && !name.isEmpty()){
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),  is.getByName(name)));
        }

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),  is.getByID(id)));
    }
    @GetMapping("/item/all")
    public ResponseEntity<ApiResponse<Object>> getAllItems(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),  is.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addRecyclingInfo(@RequestBody JsonNode body){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),  is.newItem(new JSONObject(body.toString()))));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteRecyclingInfo(@PathVariable("id") long id){
        is.deleteItem(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.ACCEPTED.value(),  "Item successfully deleted"));
    }
    @PutMapping("edit/{id}")
    public ResponseEntity<ApiResponse<Object>> editRecyclingInfo(@PathVariable("id") long id, @RequestBody JsonNode body){
        return ResponseEntity.ok( new ApiResponse<>(HttpStatus.OK.value(), is.editItem(id, new JSONObject(body.toString()))));
    }


}
