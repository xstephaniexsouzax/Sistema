
package clinica_veterinaria.repository;

import clinica_veterinaria.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByOrderByDataAgendamentoAsc();
}
