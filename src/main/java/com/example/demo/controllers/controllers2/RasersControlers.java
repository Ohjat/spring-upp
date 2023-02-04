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
@RequestMapping("raser")

public class RasersControlers {

    @Autowired
    private RasersRepo rasersRepo;

    @Autowired
    private CarsRepo carsRepo;
    @Autowired
    private GamesRepo gamesRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AwardsRepo awardsRepo;




     @GetMapping("/")
    public String authorMain(Model model) {
        Iterable<raser> raser = rasersRepo.findAll();
        model.addAttribute("raser", raser);
        return "raser/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardsAdd(raser raser,Model model) {
         Iterable<Cars> cars = carsRepo.findAll();
        model.addAttribute("cars", cars);
         Iterable<games> games = gamesRepo.findAll();
        model.addAttribute("games", games);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        Iterable<awards> awards = awardsRepo.findAll();
        model.addAttribute("awards", awards);
        return "raser/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardPostAdd(
            @ModelAttribute("raser") @Valid raser raser,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "raser/add";
        }
        rasersRepo.save(raser);
        return "redirect:";
    }

    @GetMapping("/edit/{raser}")
    @PreAuthorize("isAuthenticated()")
    public String awardsEdit(
            raser raser,
            Model model) {;
           Iterable<Cars> cars = carsRepo.findAll();
        model.addAttribute("cars", cars);
         Iterable<games> games = gamesRepo.findAll();
        model.addAttribute("games", raser.id_games);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        Iterable<awards> awards = awardsRepo.findAll();
        model.addAttribute("awards", awards);
        return "raser/edit";
    }

    @PostMapping("/edit/{raser}")
    @PreAuthorize("isAuthenticated()")
    public String awardsPostEdit(
            @ModelAttribute("raser") @Valid raser raser,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "raser/edit";
        }
        rasersRepo.save(raser);
        return "redirect:../";
    }

    @GetMapping("/show/{raser}")
    public String awardShow(
            raser raser,
            Model model) {
        model.addAttribute("raser", raser);
        return "raser/show";
    }

    @GetMapping("/del/{raser}")
    @PreAuthorize("isAuthenticated()")
    public String awardsDel(
            raser raser) {
        rasersRepo.delete(raser);
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
        return "raser/filter";
    }
}
