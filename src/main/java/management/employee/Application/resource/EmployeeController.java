package management.employee.Application.resource;

import lombok.AllArgsConstructor;
import management.employee.Application.repository.EmployeeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    String getPeople(Model model) {
        model.addAttribute("something", "coming from the controller");
        model.addAttribute("employees", employeeRepo.findAll());
        return "employees";
    }
}
