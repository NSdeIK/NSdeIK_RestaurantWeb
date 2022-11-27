package hu.inf.unideb.NSdeIK_RestaurantWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Builder
@Data
@RedisHash("Szemelyek")
public class SzemelyEntity {
    @Id
    private String szemely_id;

    @Indexed @NonNull
    private String szemely_nev;

    @Indexed
    private SzemelyPosztok szemely_poszt;

    @Indexed
    private String felhasznalonev;

    @Indexed
    @JsonIgnore
    private String jelszo;
}
