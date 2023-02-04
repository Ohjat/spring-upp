package com.example.demo.controllers.controllers2;

import com.example.demo.models.models2.type_of_race;
import com.example.demo.repo.repo2.Type_of_raceRepo;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@Controller
@RequestMapping("type_of_race")

public class Type_of_raceControlers {
    @Autowired
    private Type_of_raceRepo type_of_raceRepo;

    @GetMapping("/")
    public String Type_of_raseMain(Model model) {
        Iterable<type_of_race> type_of_race = type_of_raceRepo.findAll();
        model.addAttribute("type_of_race", type_of_race);
        return "type_of_race/main";
    }

    @GetMapping("/export")
    public void mainExelExport(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Exelfl" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);


        type_of_race[] tasks = Iterables.toArray(type_of_raceRepo.findAll(), type_of_race.class);


        ExelExport exelExport = new ExelExport(tasks);
        exelExport.generateExcelFile(response);
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String Type_of_raceAdd(type_of_race type_of_race) {
        return "type_of_race/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String Type_of_racePostAdd(
            @ModelAttribute("type_of_race") @Valid type_of_race type_of_race,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "type_of_race/add";
        }
        type_of_raceRepo.save(type_of_race);
        return "redirect:";
    }

    @GetMapping("/edit/{type_of_race}")
    @PreAuthorize("isAuthenticated()")
    public String Type_of_raceEdit(
            type_of_race type_of_race,
            Model model) {
        ;
        model.addAttribute("type_of_race", type_of_race);
        return "type_of_race/edit";
    }

    @PostMapping("/edit/{type_of_race}")
    @PreAuthorize("isAuthenticated()")
    public String Type_of_racePostEdit(
            @ModelAttribute("type_of_race") @Valid type_of_race type_of_race,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "type_of_race/edit";
        }
        type_of_raceRepo.save(type_of_race);
        return "redirect:../";
    }

    @GetMapping("/show/{type_of_race}")
    public String Type_of_raсeShow(
            type_of_race type_of_race,
            Model model) {

        model.addAttribute("type_of_race", type_of_race);
        return "type_of_race/show";
    }

    @GetMapping("/del/{type_of_race}")
    @PreAuthorize("isAuthenticated()")
    public String Type_of_raceDel(
            type_of_race type_of_race) {
        type_of_raceRepo.delete(type_of_race);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String Type_of_raceFilter(@RequestParam(defaultValue = "") String name,
                                     @RequestParam(required = false) boolean accurate_search,
                                     Model model) {
        if (!name.equals("")) {
            List<type_of_race> result = accurate_search ? type_of_raceRepo.findByName(name) : type_of_raceRepo.findByNameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "type_of_race/filter";
    }


}
