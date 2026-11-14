package AutoLoc.service;

import AutoLoc.domain.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployee(Long id);

    // Méthodes du Scénario 1
    Employee desaffecterEmploye(Long idEmploye);
    Employee affecterEmploye(Long idEmploye, Long idAgence);
}