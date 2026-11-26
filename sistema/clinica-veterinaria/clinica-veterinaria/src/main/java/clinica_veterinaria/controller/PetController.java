
package clinica_veterinaria.controller;

import clinica_veterinaria.model.Pet;
import clinica_veterinaria.repository.TutorRepository;
import clinica_veterinaria.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final TutorRepository tutorRepository;

    public PetController(PetService petService,
                         TutorRepository tutorRepository) {
        this.petService = petService;
        this.tutorRepository = tutorRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pets", petService.listar());
        return "pets/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("tutores", tutorRepository.findAll());
        return "pets/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pet pet,
                         @RequestParam Long tutorId) {
        petService.cadastrar(pet, tutorId);
        return "redirect:/pets";
    }
}
