package com.example.demo.controllers.controllers2;
import com.example.demo.models.models2.infa_employee;
import com.example.demo.models.models2.track;
import com.example.demo.repo.repo2.Info_employeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("infa_employee")
public class Infa_eployeeControlers {
        @Autowired
    private Info_employeeRepo infa_employeeRepo;

     @GetMapping("/")
    public String TrackMain(Model model) {
        Iterable<infa_employee> infa_employee = infa_employeeRepo.findAll();
        model.addAttribute("infa_employee", infa_employee);
        return "infa_employee/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String TrackAdd(infa_employee  infa_employee) {
        return "infa_employee/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String TrackPostAdd(
            @ModelAttribute("infa_employee") @Valid infa_employee infa_employee,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "infa_employee/add";
        }
        infa_employeeRepo.save(infa_employee);
        return "redirect:";
    }

    @GetMapping("/edit/{infa_employee}")
    @PreAuthorize("isAuthenticated()")
    public String TrackEdit(
            infa_employee infa_employee,
            Model model) {;
        model.addAttribute("infa_employee", infa_employee);
        return "infa_employee/edit";
    }

    @PostMapping("/edit/{infa_employee}")
    @PreAuthorize("isAuthenticated()")
    public String TrackPostEdit(
            @ModelAttribute("infa_employee") @Valid infa_employee infa_employee,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "infa_employee/edit";
        }
        infa_employeeRepo.save(infa_employee);
        return "redirect:../";
    }

    @GetMapping("/show/{infa_employee}")
    public String TrackShow(
            infa_employee infa_employee,
            Model model) {

        model.addAttribute("infa_employee", infa_employee);
        return "infa_employee/show";
    }

    @GetMapping("/del/{infa_employee}")
    @PreAuthorize("isAuthenticated()")
    public String TrackDel(
            infa_employee infa_employee) {
        infa_employeeRepo.delete(infa_employee);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String TrackFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<infa_employee> result = accurate_search ? infa_employeeRepo.findByAwards(name) : infa_employeeRepo.findByAwardsContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "infa_employee/filter";
    }


}
