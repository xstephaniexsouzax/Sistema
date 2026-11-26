
package clinica_veterinaria.controller;

import clinica_veterinaria.model.Agendamento;
import clinica_veterinaria.repository.PetRepository;
import clinica_veterinaria.service.AgendamentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final PetRepository petRepository;

    public AgendamentoController(
            AgendamentoService agendamentoService,
            PetRepository petRepository) {
        this.agendamentoService = agendamentoService;
        this.petRepository = petRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("agendamentos",
                agendamentoService.listar());

        return "agendamentos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("pets", petRepository.findAll());

        return "agendamentos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Agendamento agendamento,
                         @RequestParam(required = false) Long petId) {

        if (petId == null) {
            return "redirect:/agendamentos/novo";
        }

        agendamentoService.cadastrar(agendamento, petId);

        return "redirect:/agendamentos";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestParam LocalDate novaData) {

        agendamentoService.atualizarData(id, novaData);

        return "redirect:/agendamentos";
    }
}
