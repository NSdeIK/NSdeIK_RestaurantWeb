package hu.inf.unideb.NSdeIK_RestaurantWeb.service;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AsztalDto;
import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AsztalLefoglal;

import java.util.List;

public interface AsztalService {

    List<AsztalDto> osszes();
    AsztalDto ujAsztal(AsztalDto asztalDto);
    Object getAsztal(String id);
    AsztalLefoglal lefoglalAsztal(AsztalLefoglal asztal);
    void asztalTorles(String id);
}
