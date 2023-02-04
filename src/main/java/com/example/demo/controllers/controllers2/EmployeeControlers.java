package com.example.demo.controllers.controllers2;


import com.example.demo.models.User;
import com.example.demo.models.models2.Cars;
import com.example.demo.models.models2.details;
import com.example.demo.models.models2.employee;
import com.example.demo.models.models2.infa_employee;
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
@RequestMapping("employee")
public class EmployeeControlers {

    @Autowired
    private UserRepository userRepository;

 @Autowired
    private Info_employeeRepo info_employeeRepo;

 @Autowired
    private EmployeeRepo employeeRepo;



     @GetMapping("/")
    public String authorMain(Model model) {
        Iterable<employee> employee = employeeRepo.findAll();
        model.addAttribute("employee", employee);
        return "employee/main";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardsAdd(employee employee,Model model) {
         Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
         Iterable<infa_employee> infa_employee = info_employeeRepo.findAll();
        model.addAttribute("infa_employee", infa_employee);
        return "employee/add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String awardPostAdd(
            @ModelAttribute("employee") @Valid employee employee,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "employee/add";
        }
        employeeRepo.save(employee);
        return "redirect:";
    }

    @GetMapping("/edit/{employee}")
    @PreAuthorize("isAuthenticated()")
    public String awardsEdit(
            employee employee,
            Model model) {;
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
         Iterable<infa_employee> infa_employee = info_employeeRepo.findAll();
        model.addAttribute("infa_employee", infa_employee);
        return "employee/edit";
    }

    @PostMapping("/edit/{employee}")
    @PreAuthorize("isAuthenticated()")
    public String awardsPostEdit(
            @ModelAttribute("employee") @Valid employee employee,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            return "employee/edit";
        }
        employeeRepo.save(employee);
        return "redirect:../";
    }

    @GetMapping("/show/{employee}")
    public String awardShow(
            employee employee,
            Model model) {
         model.addAttribute("data_employee", new SimpleDateFormat("yyyy-MM-dd").format(employee.date_emploeed));
        model.addAttribute("employee", employee);
        return "employee/show";
    }

    @GetMapping("/del/{employee}")
    @PreAuthorize("isAuthenticated()")
    public String awardsDel(
            employee employee) {
        employeeRepo.delete(employee);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String awardFilter(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) boolean accurate_search,
                               Model model) {
        if (String.valueOf(name) != null) {
            List<employee> result = accurate_search ? employeeRepo.findByUserUsername(name) :  employeeRepo.findByUserUsernameContains(name);
            model.addAttribute("result", result);
        }

        model.addAttribute("name", name);
        model.addAttribute("accurate_search", accurate_search);
        return "employee/filter";
    }

}
