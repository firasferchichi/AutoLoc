package AutoLoc.controller;

import AutoLoc.domain.Employee;
import AutoLoc.service.IEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Employee", description = "API pour gérer les employés")
@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAll() { return employeeService.getAllEmployees(); }

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee) { return employeeService.addEmployee(employee); }

    @GetMapping("/get/{id}")
    public Employee get(@PathVariable Long id) { return employeeService.getEmployeeById(id); }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) { employeeService.deleteEmployee(id); }

    // --- SCÉNARIO 1 : Routes d'affectation ---

    @PutMapping("/desaffecter/{id}")
    public Employee desaffecter(@PathVariable Long id) {
        return employeeService.desaffecterEmploye(id);
    }

    @PutMapping("/affecter/{idEmploye}/{idAgence}")
    public Employee affecter(@PathVariable Long idEmploye, @PathVariable Long idAgence) {
        return employeeService.affecterEmploye(idEmploye, idAgence);
    }
}