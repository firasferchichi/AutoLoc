package AutoLoc.controller;

import AutoLoc.domain.Vehicle;
import AutoLoc.service.IVehiculeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @GetMapping("/all")
    public List<Vehicle> getAllVehicules() {
        return vehiculeService.afficherAllVehicules();
    }

    @PostMapping("/add")
    public Vehicle ajouterVehicule(@RequestBody Vehicle vehicule) {
        return vehiculeService.ajouterVehicule(vehicule);
    }

    @PutMapping("/update")
    public Vehicle modifierVehicule(@RequestBody Vehicle vehicule) {
        return vehiculeService.modifierVehicule(vehicule.getIdVehicule(), vehicule);
    }

    @GetMapping("/get/{id}")
    public Vehicle afficherVehicule(@PathVariable Long id) {
        return vehiculeService.afficherVehiculeById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void supprimerVehicule(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
    }
    @PutMapping("/affecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicle affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    @PutMapping("/desaffecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicle desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }
}