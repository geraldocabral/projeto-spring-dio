package one.digitalinnovation.projetospring.Service.Implementacao;

import one.digitalinnovation.projetospring.Models.Cliente;
import one.digitalinnovation.projetospring.Models.ClienteRepositorio;
import one.digitalinnovation.projetospring.Models.Endereco;
import one.digitalinnovation.projetospring.Models.EnderecoRepositorio;
import one.digitalinnovation.projetospring.Service.ClientService;
import one.digitalinnovation.projetospring.Service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImplementacaoClientService implements ClientService {

    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos(){
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente buscarId(Long id) {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> cliente1 = clienteRepositorio.findById(id);
        if(cliente1.isPresent()){
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepositorio.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco =enderecoRepositorio.findById(cep).orElseGet(() ->
        {
            Endereco endereco1 = viaCepService.consultarCep(cep);
            enderecoRepositorio.save(endereco1);
            return endereco1;
        });
        cliente.setEndereco(endereco);
        clienteRepositorio.save(cliente);
    }
}
