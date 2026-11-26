
package clinica_veterinaria.service;

import clinica_veterinaria.model.Pet;
import clinica_veterinaria.repository.PetRepository;
import clinica_veterinaria.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    public PetService(PetRepository petRepository,
                      TutorRepository tutorRepository) {
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
    }

    public List<Pet> listar() {
        return petRepository.findAll();
    }

    public Pet cadastrar(Pet pet, Long tutorId) {

        if (tutorId == null) {
            throw new IllegalArgumentException(
                    "É obrigatório selecionar um tutor.");
        }

        var tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() ->
                        new RuntimeException("Tutor não encontrado"));

        pet.setTutor(tutor);

        return petRepository.save(pet);
    }
}
