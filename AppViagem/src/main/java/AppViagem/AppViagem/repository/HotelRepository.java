package AppViagem.AppViagem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import AppViagem.AppViagem.models.Hotel;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

    Hotel findById(long id);
    List<Hotel> findByNome(String nome);

    // Query para a busca
    @Query(value = "select u from Hotel u where u.nome like %?1%")
    List<Hotel> findByHoteisNome(String nome);
}
