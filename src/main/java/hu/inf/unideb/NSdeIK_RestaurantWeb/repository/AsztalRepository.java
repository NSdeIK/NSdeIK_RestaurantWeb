package hu.inf.unideb.NSdeIK_RestaurantWeb.repository;

import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.AsztalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsztalRepository extends CrudRepository<AsztalEntity, String> {
}
