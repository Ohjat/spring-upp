package com.example.demo.controllers.controllers2;


import com.example.demo.models.models2.*;
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
@RequestMapping("games")
//@PreAuthorize("hasAnyAuthority('USER')")

public class GamesControlers {

 @Autowired
    private GamesRepo gamesRepo;

 @Autowired
    private TrackRepo trackRepo;

 @Autowired
    private Type_of_raceRepo type_of_raceRepo;



     @GetMapping("/")
    public String authorMain(Model model) {
        Iterable<games> games = gamesRepo.findAll();
        model.addAttribute("games", games);
        return "games/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardsAdd(games games,Model model) {
         Iterable<track> track = trackRepo.findAll();
        model.addAttribute("track", track);
         Iterable<type_of_race> type_of_race = type_of_raceRepo.findAll();
        model.addAttribute("type_of_race", type_of_race);
        return "games/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardPostAdd(
            @ModelAttribute("games") @Valid games games,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "games/add";
        }
        gamesRepo.save(games);
        return "redirect:";
    }

    @GetMapping("/edit/{games}")
    @PreAuthorize("isAuthenticated()")
    public String awardsEdit(
            games games,
            Model model) {;
         Iterable<track> track = trackRepo.findAll();
        model.addAttribute("track", track);
         Iterable<type_of_race> type_of_race = type_of_raceRepo.findAll();
        model.addAttribute("type_of_race", type_of_race);
        model.addAttribute("games", games);
        return "games/edit";
    }

    @PostMapping("/edit/{games}")
    @PreAuthorize("isAuthenticated()")
    public String awardsPostEdit(
            @ModelAttribute("games") @Valid games games,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "games/edit";
        }
        gamesRepo.save(games);
        return "redirect:../";
    }

    @GetMapping("/show/{games}")
    public String awardShow(
            games games,
            Model model) {
         model.addAttribute("data_games", new SimpleDateFormat("yyyy-MM-dd").format(games.date_games));
        model.addAttribute("games", games);
        return "games/show";
    }

    @GetMapping("/del/{games}")
    @PreAuthorize("isAuthenticated()")
    public String awardsDel(
            games games) {
        gamesRepo.delete(games);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String awardFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<games> result = accurate_search ? gamesRepo.findByTrackName(name) : gamesRepo.findByTrackNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "games/filter";
    }



}
