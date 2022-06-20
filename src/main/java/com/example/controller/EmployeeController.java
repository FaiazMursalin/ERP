package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;

@Controller
public class EmployeeController {
    //handler method to display all employees
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/")
    public String viewHomePage(Model model,@AuthenticationPrincipal UserDetails currentUser){

        model.addAttribute("listEmployees",employeeService.getAllEmployees());
//        System.out.println(employeeService.getAllEmployees().get(0));
        if(currentUser!=null) {
            model.addAttribute("current_user",currentUser);
        }
        return "index";
    }

    @RequestMapping("/newEmployeeForm")
    public String newEmployeeForm(Model model,@AuthenticationPrincipal UserDetails currentUser){
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        if(currentUser!=null) {
            model.addAttribute("current_user",currentUser);
        }
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid  @ModelAttribute("employee") Employee employee, BindingResult bindingResult,String role,String request){
        //saving to database

        if(role == null || role.isBlank() || role.isEmpty()){
            employee.setRole("USER");
        }

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "new_employee";
        }

        employee.setPass((new BCryptPasswordEncoder()).encode(employee.getPass()));
        employeeService.saveEmployee(employee);


        /*
        boolean thereAreErrors = bindingResult.hasErrors();
        if(!thereAreErrors) {
            if(request.equals("new")){
                employee.setPass((new BCryptPasswordEncoder()).encode(employee.getPass()));
            }
            if(role == null){

                employee.setRole("USER");
            }


            employeeService.saveEmployee(employee);
            return "redirect:/";
        }

        System.out.println(bindingResult.getAllErrors());
        if(request.equals("new")){
            return "redirect:/newEmployeeForm";
        }else if(request.equals("update")){
            return "redirect:/updateEmployeeForm/"+employee.getId();
        }else{
            System.out.println(request);
            return "redirect:/";
        }*/

        return "redirect:/";
    }
    @GetMapping("/updateEmployeeForm/{id}")
    public String updateEmployeeForm(@PathVariable (value = "id") long id, Model model) throws EmployeeNotFoundException {
        //get employee from service
        Employee employee = employeeService.getEmployeeById(id);
        //set the employee as a model and populate the form
        model.addAttribute("employee",employee);
        return "update_employee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id,Model model){
        this.employeeService.deleteEmployee(id);
        return "redirect:/";
    }

}
