
package clinica_veterinaria.service;

import clinica_veterinaria.model.Agendamento;
import clinica_veterinaria.repository.AgendamentoRepository;
import clinica_veterinaria.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PetRepository petRepository;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            PetRepository petRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.petRepository = petRepository;
    }

    public List<Agendamento> listar() {
        return agendamentoRepository
                .findAllByOrderByDataAgendamentoAsc();
    }

    public Agendamento cadastrar(Agendamento agendamento,
                                 Long petId) {

        if (petId == null) {
            throw new IllegalArgumentException(
                    "É obrigatório selecionar um pet.");
        }

        var pet = petRepository.findById(petId)
                .orElseThrow(() ->
                        new RuntimeException("Pet não encontrado"));

        agendamento.setPet(pet);

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizarData(Long id, LocalDate novaData) {

        if (novaData == null) {
            throw new IllegalArgumentException(
                    "A nova data é obrigatória.");
        }

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Agendamento não encontrado"));

        agendamento.setDataAgendamento(novaData);

        return agendamentoRepository.save(agendamento);
    }
}
