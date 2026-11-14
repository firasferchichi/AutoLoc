package AutoLoc.controller;

import AutoLoc.domain.Agence;
import AutoLoc.service.IAgenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Agence", description = "API pour gérer les agences")
@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceController {

    private final IAgenceService agenceService;

    @GetMapping("/all")
    public List<Agence> getAll() { return agenceService.getAllAgences(); }

    @PostMapping("/add")
    public Agence add(@RequestBody Agence agence) { return agenceService.addAgence(agence); }

    @GetMapping("/get/{id}")
    public Agence get(@PathVariable Long id) { return agenceService.getAgenceById(id); }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) { agenceService.deleteAgence(id); }

    // Route pour le Scénario 2
    @PostMapping("/addWithVehicules")
    public Agence addWithVehicules(@RequestBody Agence agence) {
        return agenceService.ajouterAgenceEtAffecterVehicules(agence);
    }
}