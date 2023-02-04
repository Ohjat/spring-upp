package com.example.demo.controllers.controllers2;
import com.example.demo.models.models2.rol;
import com.example.demo.models.models2.track;
import com.example.demo.models.models2.type_of_race;
import com.example.demo.repo.repo2.RolRepo;
import com.example.demo.repo.repo2.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("rol")
public class RolControlers {

    @Autowired
    private RolRepo rolRepo;

    @GetMapping("/")
    public String RolMain(Model model) {
        Iterable<rol> rol = rolRepo.findAll();
        model.addAttribute("rol", rol);
        return "rol/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String RolAdd(rol  rol) {
        return "rol/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String RolPostAdd(
            @ModelAttribute("rol") @Valid rol rol,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "rol/add";
        }
        rolRepo.save(rol);
        return "redirect:";
    }

    @GetMapping("/edit/{rol}")
    @PreAuthorize("isAuthenticated()")
    public String RolEdit(
            rol rol,
            Model model) {;
        model.addAttribute("rol", rol);
        return "rol/edit";
    }

    @PostMapping("/edit/{rol}")
    @PreAuthorize("isAuthenticated()")
    public String RolPostEdit(
            @ModelAttribute("rol") @Valid rol rol,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "rol/edit";
        }
        rolRepo.save(rol);
        return "redirect:../";
    }

    @GetMapping("/show/{rol}")
    public String RolShow(
            rol rol,
            Model model) {

        model.addAttribute("rol", rol);
        return "rol/show";
    }

    @GetMapping("/del/{rol}")
    @PreAuthorize("isAuthenticated()")
    public String RolDel(
            rol rol) {
        rolRepo.delete(rol);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String RolFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<rol> result = accurate_search ? rolRepo.findByName(name) : rolRepo.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "rol/filter";
    }


}
