
package clinica_veterinaria.controller;

import clinica_veterinaria.model.Tutor;
import clinica_veterinaria.service.TutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String busca,
            Model model) {

        if (busca != null && !busca.isBlank()) {
            model.addAttribute("tutores", tutorService.buscar(busca));
        } else {
            model.addAttribute("tutores", tutorService.listar());
        }

        model.addAttribute("busca", busca);

        return "tutores/lista";
    }

    @GetMapping("/novo")
    public String novo() {
        return "tutores/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Tutor tutor,
            @RequestParam String cpf) {

        tutorService.cadastrar(tutor, cpf);
        return "redirect:/tutores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tutor", tutorService.buscarPorId(id));
        return "tutores/editar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @ModelAttribute Tutor tutor) {

        tutorService.atualizar(id, tutor);
        return "redirect:/tutores";
    }

}
