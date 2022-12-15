package hu.inf.unideb.NSdeIK_RestaurantWeb.repository;

import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.MegrendelesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MegrendelesRepository extends CrudRepository<MegrendelesEntity, String> {
    List<MegrendelesEntity> findAllByAsztalid(String id);
}
