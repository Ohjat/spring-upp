package com.example.demo.controllers.controllers2;
import com.example.demo.models.models2.track;
import com.example.demo.models.models2.type_of_race;
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
@RequestMapping("track")
public class TrackControlers {

        @Autowired
    private TrackRepo trackRepo;

     @GetMapping("/")
    public String TrackMain(Model model) {
        Iterable<track> track = trackRepo.findAll();
        model.addAttribute("track", track);
        return "track/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String TrackAdd(track  track) {
        return "track/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String TrackPostAdd(
            @ModelAttribute("track") @Valid track track,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "track/add";
        }
        trackRepo.save(track);
        return "redirect:";
    }

    @GetMapping("/edit/{track}")
    @PreAuthorize("isAuthenticated()")
    public String TrackEdit(
            track track,
            Model model) {;
        model.addAttribute("track", track);
        return "track/edit";
    }

    @PostMapping("/edit/{track}")
    @PreAuthorize("isAuthenticated()")
    public String TrackPostEdit(
            @ModelAttribute("track") @Valid track track,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "track/edit";
        }
        trackRepo.save(track);
        return "redirect:../";
    }

    @GetMapping("/show/{track}")
    public String TrackShow(
            track track,
            Model model) {

        model.addAttribute("track", track);
        return "track/show";
    }

    @GetMapping("/del/{track}")
    @PreAuthorize("isAuthenticated()")
    public String TrackDel(
            track track) {
        trackRepo.delete(track);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String TrackFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (!name.equals("")) {
            List<track> result = accurate_search ? trackRepo.findByName(name) : trackRepo.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "track/filter";
    }



}
