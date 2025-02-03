package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Model.TipModel;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.ResponseHandler.ApiResponse;
import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service.TipService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tip")
public class TipController {
    TipService tips = new TipService();
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getTip(@RequestParam(required = false) Long id){
        TipModel tip;
        if (id != null){
            tip = tips.getRandom();
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.FOUND.value(), tip));
        }
        tip = tips.getRandom();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.FOUND.value(), tip));
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAll(){
        List<TipModel> all = tips.getAll();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.FOUND.value(), all));

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addingNewTip(@RequestBody JsonNode body){
        TipModel t = tips.addTip(new JSONObject(body.toString()));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.FORBIDDEN.value(), t));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteTip(@PathVariable("id") long id){
        tips.deleteTip(id);
        return ResponseEntity.accepted().body(new ApiResponse<>(HttpStatus.ACCEPTED.value(), "Item with ID has been deleted" ));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<Object>> editTip(@PathVariable("id") long id, @RequestBody JsonNode body){
        TipModel tm = tips.editTip(id, new JSONObject(body.toString()));
        return ResponseEntity.ok().body(new ApiResponse<>(200, tm));
    }
}
