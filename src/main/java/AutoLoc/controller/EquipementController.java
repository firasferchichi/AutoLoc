package AutoLoc.controller;

import AutoLoc.domain.Equipement;
import AutoLoc.service.IEquipementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Equipement", description = "API pour gérer les équipements")
@RestController
@AllArgsConstructor
@RequestMapping("/equipement")
public class EquipementController {

    private final IEquipementService equipementService;

    @GetMapping("/all")
    public List<Equipement> getAll() {
        return equipementService.getAllEquipements();
    }

    @PostMapping("/add")
    public Equipement add(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    @GetMapping("/get/{id}")
    public Equipement get(@PathVariable Long id) {
        return equipementService.getEquipementById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
    }
}