package one.digitalinnovation.projetospring.Service;

import one.digitalinnovation.projetospring.Models.Cliente;

public interface ClientService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);



}
