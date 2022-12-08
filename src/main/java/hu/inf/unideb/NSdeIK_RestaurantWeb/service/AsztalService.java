package hu.inf.unideb.NSdeIK_RestaurantWeb.service;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.AsztalDto;

import java.util.List;

public interface AsztalService {

    List<AsztalDto> osszes();
    AsztalDto ujAsztal(AsztalDto asztalDto);

    void asztalTorles(String id);
}
