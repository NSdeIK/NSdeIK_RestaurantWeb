package hu.inf.unideb.NSdeIK_RestaurantWeb.service;

import hu.inf.unideb.NSdeIK_RestaurantWeb.dto.EtlapDto;

import java.util.List;

public interface EtlapService {
    EtlapDto etlapMentes(EtlapDto etlapDto);
    List<EtlapDto> osszesEtlap();

}
