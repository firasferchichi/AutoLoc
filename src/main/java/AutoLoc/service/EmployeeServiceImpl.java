package AutoLoc.service;

import AutoLoc.domain.Employee;
import AutoLoc.domain.Agence;
import AutoLoc.repository.EmployeeRepository;
import AutoLoc.repository.AgenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AgenceRepository agenceRepository; // Nécessaire pour le scénario 1

    @Override
    public List<Employee> getAllEmployees() { return employeeRepository.findAll(); }

    @Override
    public Employee addEmployee(Employee employee) { return employeeRepository.save(employee); }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setNom(employee.getNom());
            existing.setPrenom(employee.getPrenom());
            existing.setRole(employee.getRole());
            return employeeRepository.save(existing);
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(Long id) { return employeeRepository.findById(id).orElse(null); }

    @Override
    public void deleteEmployee(Long id) { employeeRepository.deleteById(id); }

    // --- SCÉNARIO 1 : AFFECTATION / DÉSAFFECTATION ---

    @Override
    @Transactional
    public Employee desaffecterEmploye(Long idEmploye) {
        Employee employe = employeeRepository.findById(idEmploye).orElse(null);
        if (employe != null) {
            employe.setAgence(null); // On retire l'agence
            return employeeRepository.save(employe);
        }
        return null;
    }

    @Override
    @Transactional
    public Employee affecterEmploye(Long idEmploye, Long idAgence) {
        Employee employe = employeeRepository.findById(idEmploye).orElse(null);
        Agence agence = agenceRepository.findById(idAgence).orElse(null);

        if (employe != null && agence != null) {
            employe.setAgence(agence); // On affecte la nouvelle agence
            return employeeRepository.save(employe);
        }
        return null;
    }
}