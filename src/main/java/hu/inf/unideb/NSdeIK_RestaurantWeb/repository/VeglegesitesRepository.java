package hu.inf.unideb.NSdeIK_RestaurantWeb.repository;

import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.VeglegesitesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeglegesitesRepository extends CrudRepository<VeglegesitesEntity, String>
{

}
