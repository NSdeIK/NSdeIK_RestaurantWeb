package hu.inf.unideb.NSdeIK_RestaurantWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@Builder
public class AsztalDto implements Serializable {
    private static final long serialVersionUID = -3896901233795527733L;

    private String asztal_id;
    private String asztal_nev;
    private Integer maxfo;
    private String statusz;
}
