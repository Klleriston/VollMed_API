package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
    @NotBlank String logradouro, 
    @NotBlank String bairro, 
    @NotBlank String cidade, 
    @NotBlank String uf, 
    String complemento, 
    String numero, 
    @NotBlank @Pattern(regexp = "\\d{8}") String cep) {

}
