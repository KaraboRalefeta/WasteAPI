package enviro.assessment.grad001.karaboRalefeta.RecycleAPI.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recycle")
public class RecycleController {

    @GetMapping("/{item}")
    public String recyclingInfo(@PathVariable String item){
        // should return true/false if the item can be recycled
        // and how to recycle it if it can be recycled
        // or what can be done about it if it can't be recycled

        // im considering having alternative for what can be done for the
        // recyclable things to be included
        return null;
    }

    @PostMapping("/add")
    public void AddRecyclingInfo(){
        // not sure if i should request all the info or what
        // request all info for now
    }

}
