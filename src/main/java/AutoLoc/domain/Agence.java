package AutoLoc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "agence")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(nullable = false, length = 100)
    private String adresse;

    @Column(nullable = false, length = 20)
    private String telephone;

    // --- Associations ---
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private List<Vehicle> vehicules;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private List<Employee> employes;
}