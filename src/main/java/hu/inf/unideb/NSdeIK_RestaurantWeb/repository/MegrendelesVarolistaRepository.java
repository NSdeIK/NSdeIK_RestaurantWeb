package hu.inf.unideb.NSdeIK_RestaurantWeb.repository;

import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.MegrendelesVarolistaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MegrendelesVarolistaRepository extends CrudRepository<MegrendelesVarolistaEntity, String> {

    List<MegrendelesVarolistaEntity> findAllByAsztalid(String id);
}
