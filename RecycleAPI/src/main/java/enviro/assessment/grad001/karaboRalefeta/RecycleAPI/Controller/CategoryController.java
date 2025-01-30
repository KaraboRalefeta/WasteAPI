package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @GetMapping("/{material}")
    public List<String> material(@PathVariable String material){
        // return example of items that fall under that material
        return null;
    }
    @GetMapping("/categories")
    public List<String> getCategories(){
        // returns a list of all the categories in the system
        return null;
    }

    @PostMapping("/add")
    public void addingNewObject(){
        // both the material and the object should be provided
        // check for whether the material exist first
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategories(@PathVariable("id") long id){
        // do send a code to show it's successful
    }
    @DeleteMapping("/delete/{material}/{id}")
    public void deleteMaterial(@PathVariable("material") String materialId, @PathVariable("id") long id){
        // do send a code to show it's successful
    }

    @PutMapping("/edit/{id}")
    public void editCategories(@PathVariable("id") long id){
        // edit a specif Categories, do get the id first
    }
}
