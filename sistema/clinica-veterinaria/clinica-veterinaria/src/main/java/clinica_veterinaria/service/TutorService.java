
package clinica_veterinaria.service;

import clinica_veterinaria.model.Tutor;
import clinica_veterinaria.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;
    private final CriptografiaService criptografiaService;

    public TutorService(TutorRepository tutorRepository,
                        CriptografiaService criptografiaService) {
        this.tutorRepository = tutorRepository;
        this.criptografiaService = criptografiaService;
    }

    public Tutor cadastrar(Tutor tutor, String cpf) {

        String cpfIndice = criptografiaService.gerarIndiceCpf(cpf);

        if (tutorRepository.existsByCpfIndice(cpfIndice)) {
            throw new IllegalArgumentException(
                    "Já existe um tutor cadastrado com esse CPF.");
        }

        tutor.setCpfIndice(cpfIndice);
        tutor.setCpfCriptografado(
                criptografiaService.criptografar(cpf));

        return tutorRepository.save(tutor);
    }

    public List<Tutor> listar() {
        return tutorRepository.findAll();
    }

    public List<Tutor> buscar(String termo) {
        return tutorRepository
                .findByNomeContainingIgnoreCaseOrTelefoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        termo, termo, termo);
    }

    public Tutor buscarPorId(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tutor não encontrado"));
    }

    public Tutor atualizar(Long id, Tutor dados) {
        Tutor tutor = buscarPorId(id);

        tutor.setNome(dados.getNome());
        tutor.setTelefone(dados.getTelefone());
        tutor.setEmail(dados.getEmail());
        tutor.setEndereco(dados.getEndereco());

        return tutorRepository.save(tutor);
    }
}
