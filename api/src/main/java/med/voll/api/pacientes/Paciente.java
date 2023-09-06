package med.voll.api.pacientes;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    public Paciente(DadosCadastroPacientes dadosP) {
        this.ativo = true;
        this.nome = dadosP.nome();
        this.CPF = dadosP.CPF();
        this.email = dadosP.email();
        this.telefone = dadosP.telefone();
        this.endereco = new endereco(dadosP.endereco());
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String CPF;
    private String email;
    private String telefone;
    
    @Embedded
    private endereco endereco;

    private boolean ativo;

    public void atualizarInformacoes(@Valid DadosAtualizarPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
