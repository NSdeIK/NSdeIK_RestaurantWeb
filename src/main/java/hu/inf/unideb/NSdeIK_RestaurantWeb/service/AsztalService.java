package hu.inf.unideb.NSdeIK_RestaurantWeb.service;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.*;
import hu.inf.unideb.NSdeIK_RestaurantWeb.entity.MegrendelesEntity;

import java.util.List;

public interface AsztalService {

    List<AsztalDto> osszes();
    AsztalDto ujAsztal(AsztalDto asztalDto);
    Object getAsztal(String id);
    AsztalLefoglal lefoglalAsztal(AsztalLefoglal asztal);
    void asztalTorles(String id);
    void veglegesites(VeglegesitesDto veglegesitesDto);
    MegrendelesVarolistaDto ujMegrendelesVarolista(MegrendelesVarolistaDto megrendelesVarolistaDto);
    MegrendelesDto ujMegrendeles(MegrendelesDto megrendelesVarolistaDto);
    List<MegrendelesVarolistaDto> osszesMegrendelesek();
    MegrendelesEntity hozzaadMegrendelesekhez(String id);
    void torlesMegrendelesVarolista(String id);
}
