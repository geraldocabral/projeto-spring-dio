package one.digitalinnovation.projetospring.Models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositorio extends CrudRepository<Endereco, String> {
}
