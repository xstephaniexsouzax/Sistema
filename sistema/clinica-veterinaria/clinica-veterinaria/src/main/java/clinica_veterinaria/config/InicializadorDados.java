
package clinica_veterinaria.config;

import clinica_veterinaria.model.Usuario;
import clinica_veterinaria.repository.UsuarioRepository;
import clinica_veterinaria.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InicializadorDados {

    @Bean
    public CommandLineRunner criarUsuario(
            UsuarioRepository repository,
            UsuarioService usuarioService) {

        return args -> {
            if (repository.findByUsername("admin").isEmpty()) {

                Usuario usuario = new Usuario();

                usuario.setNome("Administrador");
                usuario.setUsername("admin");
                usuario.setSenha("Admin@123");

                usuarioService.cadastrar(usuario);
            }
        };
    }
}
