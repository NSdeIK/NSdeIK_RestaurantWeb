package hu.inf.unideb.NSdeIK_RestaurantWeb.repository;

import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.SzemelyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SzemelyRepository extends CrudRepository<SzemelyEntity, String> {
    SzemelyEntity findByFelhasznalonev(String felhasznalonev);
}
