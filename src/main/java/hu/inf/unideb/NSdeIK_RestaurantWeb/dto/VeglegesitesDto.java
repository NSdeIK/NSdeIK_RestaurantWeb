package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@Builder
public class VeglegesitesDto {
    private static final long serialVersionUID = -6393369454514410782L;

    private String id;
    private String asztal_nev;
    private String asztal_id;
    private String kezelo_pincer_nev;
    private Integer kifizetesi_osszeg;
    private String mikor_veglegesitette;
}
