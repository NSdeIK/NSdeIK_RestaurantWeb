package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@Builder
public class SzemelyDto implements Serializable {

    private static final long serialVersionUID = 456123789L;

    private String szemely_id;
    private String szemely_nev;
    private SzemelyPosztok szemely_poszt;
    private String felhasznalonev;
    @JsonIgnore
    private String jelszo;
}
