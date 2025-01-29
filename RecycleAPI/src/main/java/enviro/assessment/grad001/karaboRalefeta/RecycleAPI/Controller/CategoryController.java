package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/{material}")
    public List<String> material(@PathVariable String material){
        // return example of things that fall under that material
        return null;
    }
    @PostMapping("/add")
    public void addingNewObject(){
        // both the material and the object should be provided
        // check for whether the material exist first
    }




}
