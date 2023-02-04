package com.example.demo.controllers.controllers2;


import com.example.demo.models.models2.Cars;
import com.example.demo.models.models2.details;
import com.example.demo.models.models2.warehouse;
import com.example.demo.repo.repo2.CarsRepo;
import com.example.demo.repo.repo2.DetailsRepo;
import com.example.demo.repo.repo2.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("details")
//@PreAuthorize("hasAnyAuthority('USER')")

public class DetailsControlers {

    @Autowired
    private DetailsRepo detailsRepo;

    @Autowired
    private CarsRepo carsRepo;

     @Autowired
    private WarehouseRepo warehouseRepo;

     @GetMapping("/")
    public String detailsMain(Model model) {
        Iterable<details> details = detailsRepo.findAll();
        model.addAttribute("details", details);
        return "details/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String carsAdd(details details , Model model)
    {
        Iterable<Cars> Cars = carsRepo.findAll();
        model.addAttribute("Cars", Cars);
        return "details/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String detailsPostAdd(
            @ModelAttribute("details") @Valid details details,
            BindingResult bindingResult
    )
    {
        if (bindingResult.hasErrors()){
            return "details/add";
        }
        detailsRepo.save(details);
        return "redirect:";
    }

    @GetMapping("/edit/{details}")
    @PreAuthorize("isAuthenticated()")
    public String detailsEdit(
            details details,
            Model model) {;
         Iterable<Cars> Cars = carsRepo.findAll();
        model.addAttribute("Cars", Cars);
        model.addAttribute("details", details);
        return "details/edit";
    }

    @PostMapping("/edit/{details}")
    @PreAuthorize("isAuthenticated()")
    public String detailsPostEdit(
            @ModelAttribute("details") @Valid details details,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "details/edit";
        }
        detailsRepo.save(details);
        return "redirect:../";
    }

    @GetMapping("/show/{details}")
    public String detailsShow(
            details details,
            Model model) {
         model.addAttribute("details", details);
        return "details/show";
    }

    @GetMapping("/del/{details}")
    @PreAuthorize("isAuthenticated()")
    public String detailsDel(
            details details) {
        detailsRepo.delete(details);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String detailsFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<details> result = accurate_search ? detailsRepo.findByName(name) : detailsRepo.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "details/filter";
    }

    @GetMapping("/grafik")
    public String clientNumberChart(Model model) {
        model.addAttribute("slads",
                getTariffList(((List) warehouseRepo.findAll())));
        model.addAttribute("details", getDetailsList((List) detailsRepo.findAll()));
        return "details/grafik";
    }

    private static List<String> getDetailsList(List<details> details) {
        List<String> tariffNames = new ArrayList<>();
        for (var detail : details) {
            tariffNames.add(detail.getName());
        }
        return tariffNames;
    }
    private static List<Double> getTariffList(List<warehouse> warehouse) {
        List<Double> tariffNames = new ArrayList<>();
        for (var warehouses : warehouse) {
            tariffNames.add((double) warehouses.getDetails().getQuantity());
        }
        return tariffNames;
    }

}
