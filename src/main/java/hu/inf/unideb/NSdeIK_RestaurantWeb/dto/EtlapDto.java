package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.EtlapTipusok;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@Builder
public class EtlapDto {
    private static final long serialVersionUID = -2035526680637677208L;

    private String etlap_id;
    private EtlapTipusok etlap_tipus;
    private String etlap_neve;
    private Integer etlap_ara;
}
