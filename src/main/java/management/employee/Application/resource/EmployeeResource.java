package management.employee.Application.resource;

import lombok.AllArgsConstructor;
import management.employee.Application.controller.EmployeeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class EmployeeResource {

    private final EmployeeController employeeController;

    @GetMapping(path = "/employees")
    public String getEmployees(Model model) {
        model.addAttribute("something", "coming from the controller");
        model.addAttribute("employees", employeeController.getEmployees());
        return "employees";
    }
}
