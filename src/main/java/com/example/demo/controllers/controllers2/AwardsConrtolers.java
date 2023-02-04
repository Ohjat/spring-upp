package com.example.demo.controllers.controllers2;


import com.example.demo.models.models2.awards;
import com.example.demo.repo.repo2.AwardsRepo;
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
@RequestMapping("awards")
public class AwardsConrtolers {

    @Autowired
    private AwardsRepo awardsRepo;

     @GetMapping("/")
    public String authorMain(Model model) {
        Iterable<awards> awards = awardsRepo.findAll();
        model.addAttribute("awards", awards);
        return "awards/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardsAdd(awards awards) {
        return "awards/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardPostAdd(
            @ModelAttribute("awards") @Valid awards awards,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "awards/add";
        }
        awardsRepo.save(awards);
        return "redirect:";
    }

    @GetMapping("/edit/{awards}")
    @PreAuthorize("isAuthenticated()")
    public String awardsEdit(
            awards awards,
            Model model) {;
        model.addAttribute("award", awards);
        return "awards/edit";
    }

    @PostMapping("/edit/{awards}")
    @PreAuthorize("isAuthenticated()")
    public String awardsPostEdit(
            @ModelAttribute("awards") @Valid awards awards,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "awards/edit";
        }
        awardsRepo.save(awards);
        return "redirect:../";
    }

    @GetMapping("/show/{awards}")
    public String awardShow(
            awards awards,
            Model model) {
         model.addAttribute("release_date", new SimpleDateFormat("yyyy-MM-dd").format(awards.date));
        model.addAttribute("awards", awards);
        return "awards/show";
    }

    @GetMapping("/del/{awards}")
    @PreAuthorize("isAuthenticated()")
    public String awardsDel(
            awards awards) {
        awardsRepo.delete(awards);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String awardFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<awards> result = accurate_search ? awardsRepo.findByName(name) : awardsRepo.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "awards/filter";
    }




}
