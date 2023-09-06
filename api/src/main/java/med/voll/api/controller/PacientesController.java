package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.pacientes.DadosAtualizarPaciente;
import med.voll.api.pacientes.DadosCadastroPacientes;
import med.voll.api.pacientes.DadosListagemPaciente;
import med.voll.api.pacientes.Paciente;
import med.voll.api.pacientes.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacientesController {

    @Autowired
    private PacienteRepository repositoryP;
    
    @PostMapping
    @Transactional
    public void cadastroDePaciente(@RequestBody @Valid DadosCadastroPacientes dadosPaciente) {
        repositoryP.save(new Paciente(dadosPaciente));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarP(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repositoryP.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        var paciente = repositoryP.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repositoryP.getReferenceById(id);
        paciente.excluir();
    }
}
