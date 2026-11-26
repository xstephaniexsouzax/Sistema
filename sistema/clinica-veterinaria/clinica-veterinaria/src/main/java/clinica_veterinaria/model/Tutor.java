
package clinica_veterinaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Column(nullable = false)
    private String cpfCriptografado;

    @Column(unique = true, nullable = false)
    private String cpfIndice;

    private String telefone;

    private String email;

    private String endereco;

    public Tutor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getCpfCriptografado() {
        return cpfCriptografado;
    }

    public void setCpfCriptografado(String cpfCriptografado) {
        this.cpfCriptografado = cpfCriptografado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCpfIndice() {
        return cpfIndice;
    }

    public void setCpfIndice(String cpfIndice) {
        this.cpfIndice = cpfIndice;
    }
}
