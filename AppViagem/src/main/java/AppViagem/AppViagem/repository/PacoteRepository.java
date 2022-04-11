package AppViagem.AppViagem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import AppViagem.AppViagem.models.Pacote;

import java.util.List;

public interface PacoteRepository extends CrudRepository<Pacote, Long> {

    Pacote findById(long id);
    Pacote findByDestino(String destino);

    // Query para a busca
    @Query(value = "select u from Pacote u where u.destino like %?1%")
    List<Pacote> findByDestinosPacote(String destino);
}
