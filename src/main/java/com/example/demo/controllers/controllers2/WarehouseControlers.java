package com.example.demo.controllers.controllers2;


import com.example.demo.models.User;
import com.example.demo.models.models2.*;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.repo2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping("warehouse")
public class WarehouseControlers {

    @Autowired
    private DetailsRepo detailsRepo;

 @Autowired
    private EmployeeRepo employeeRepo;

 @Autowired
    private WarehouseRepo warehouseRepo;



     @GetMapping("/")
    public String authorMain(Model model) {
        Iterable<warehouse> warehouse = warehouseRepo.findAll();
        model.addAttribute("warehouse", warehouse);
        return "warehouse/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardsAdd(warehouse warehouse,Model model) {
         Iterable<details> details = detailsRepo.findAll();
        model.addAttribute("details", details);
         Iterable<employee> employee = employeeRepo.findAll();
        model.addAttribute("employee", employee);
        return "warehouse/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardPostAdd(
            @ModelAttribute("warehouse") @Valid warehouse warehouse,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "warehouse/add";
        }
        warehouseRepo.save(warehouse);
        return "redirect:";
    }

    @GetMapping("/edit/{warehouse}")
    @PreAuthorize("isAuthenticated()")
    public String awardsEdit(
            warehouse warehouse,
            Model model) {;
            Iterable<details> details = detailsRepo.findAll();
        model.addAttribute("details", details);
         Iterable<employee> employee = employeeRepo.findAll();
        model.addAttribute("employee", employee);
        return "warehouse/edit";
    }

    @PostMapping("/edit/{warehouse}")
    @PreAuthorize("isAuthenticated()")
    public String awardsPostEdit(
            @ModelAttribute("warehouse") @Valid warehouse warehouse,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "warehouse/edit";
        }
        warehouseRepo.save(warehouse);
        return "redirect:../";
    }

    @GetMapping("/show/{warehouse}")
    public String awardShow(
            warehouse warehouse,
            Model model) {
        model.addAttribute("warehouse", warehouse);
        return "warehouse/show";
    }

    @GetMapping("/del/{warehouse}")
    @PreAuthorize("isAuthenticated()")
    public String awardsDel(
            warehouse warehouse) {
        warehouseRepo.delete(warehouse);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String awardFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<warehouse> result = accurate_search ? warehouseRepo.findByName(name) : warehouseRepo.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "warehouse/filter";
    }


}
