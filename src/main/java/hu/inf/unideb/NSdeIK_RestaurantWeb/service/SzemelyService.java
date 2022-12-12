package hu.inf.unideb.NSdeIK_RestaurantWeb.service;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.SzemelyDto;
import java.util.List;

public interface SzemelyService
{
    SzemelyDto getById(String id);
    List<SzemelyDto> osszes();
    List<SzemelyDto> szemelyekLista();
    SzemelyDto szemelyMentes(SzemelyDto szemelyDto);
    void szemelyTorles(String id);
}
