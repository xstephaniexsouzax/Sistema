
package clinica_veterinaria.repository;

import clinica_veterinaria.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByCpfIndice(String cpfIndice);

    List<Tutor> findByNomeContainingIgnoreCaseOrTelefoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String nome, String telefone, String email
    );
}
