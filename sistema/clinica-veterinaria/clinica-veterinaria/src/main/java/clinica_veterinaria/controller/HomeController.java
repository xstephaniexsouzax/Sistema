
package clinica_veterinaria.controller;

import clinica_veterinaria.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UsuarioRepository usuarioRepository;

    public HomeController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {

        var usuario = usuarioRepository
                .findByUsername(authentication.getName())
                .orElseThrow();

        model.addAttribute("nomeUsuario", usuario.getNome());

        return "home";
    }
}
