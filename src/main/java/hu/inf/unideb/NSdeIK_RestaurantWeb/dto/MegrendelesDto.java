package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@Builder
public class MegrendelesDto {
    private static final long serialVersionUID = -276162847632624762L;

    private String id;
    private String asztalid;
    private String megrendeles_id;
    private String megrendeles_neve;
    private Integer megrendeles_db;
    private String megrendeles_ara;
    private String szakacs;
}
