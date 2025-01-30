package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Controller;

import enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Service.TipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tip")
public class TipController {
    TipService tips = new TipService();
    @GetMapping
    public String getTip(@RequestParam(required = false) Long id){
        // return specific tip or a random tip
        if (id != null){
            return tips.getByID(id);
        }
        return tips.getRandom();
    }
    @GetMapping("/all")
    public List<String> getAll(){
        // return all tips
        return tips.getAll();
    }

    @PostMapping("/add")
    public void addingNewTip(){
        // both the material and the object should be provided
        // check for whether the material exist first
    }

}
