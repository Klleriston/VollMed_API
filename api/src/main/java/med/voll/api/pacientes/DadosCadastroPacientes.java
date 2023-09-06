package med.voll.api.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroPacientes (
    @NotBlank String nome, 
    @NotBlank @Pattern(regexp = "\\d{11}") String CPF, 
    @NotBlank @Email String email, 
    @NotBlank String telefone, 
    @NotNull @Valid DadosEndereco endereco) {

}
