
package clinica_veterinaria.repository;

import clinica_veterinaria.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
