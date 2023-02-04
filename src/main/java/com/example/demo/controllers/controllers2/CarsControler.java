package com.example.demo.controllers.controllers2;


import com.example.demo.models.models2.Cars;
import com.example.demo.models.models2.details;
import com.example.demo.repo.repo2.CarsRepo;
import com.example.demo.repo.repo2.DetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("cars")
//@PreAuthorize("hasAnyAuthority('USER')")

public class CarsControler {

  @Autowired
    private CarsRepo carsRepo;

    @Autowired
    private DetailsRepo detailsRepo;

     @GetMapping("/")
    public String carsMain(Model model) {
        Iterable<Cars> cars = carsRepo.findAll();
        model.addAttribute("cars", cars);
        return "cars/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String carsAdd(Cars cars, Model model) {
        Iterable<details> details = detailsRepo.findAll();
        model.addAttribute("details", details);

         return "cars/add";
    }
@PostMapping("/delete")
    public String cartDelete(@RequestParam long id , Model model) {
        Cars cars = carsRepo.findById(id).get();
        carsRepo.delete(cars);
         Iterable<Cars> car = carsRepo.findAll();
        model.addAttribute("cars", car);
        return "cars/main";
    }
    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String carsPostAdd(
            @ModelAttribute("cars") @Valid Cars cars,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "cars/add";
        }
        carsRepo.save(cars);
        return "redirect:";
    }

    @GetMapping("/edit/{cars}")
    @PreAuthorize("isAuthenticated()")
    public String carsEdit(
            Cars cars,
            Model model) {;
          Iterable<details> details = detailsRepo.findAll();
        model.addAttribute("details", details);
        model.addAttribute("cars", cars);
        return "cars/edit";
    }

    @PostMapping("/edit/{cars}")
    @PreAuthorize("isAuthenticated()")
    public String carsPostEdit(
            @ModelAttribute("cars") @Valid Cars cars,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "cars/edit";
        }
        carsRepo.save(cars);
        return "redirect:../";
    }

    @GetMapping("/show/{cars}")
    public String carsShow(
            Cars cars,
            Model model) {
         model.addAttribute("cars", cars);
        return "cars/show";
    }

    @GetMapping("/del/{cars}")
    @PreAuthorize("isAuthenticated()")
    public String carsDel(
            @Valid Cars cars) {
        carsRepo.delete(cars);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String carsFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<Cars> result = accurate_search ? carsRepo.findByName(name) : carsRepo.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "cars/filter";
    }





}
