package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@Builder
public class AsztalLefoglal {
    private static final long serialVersionUID = 6801458761408116382L;

    private String asztal_id;
    private String kezelo_szemely_id;
    private Integer vendegek_szama;
}
