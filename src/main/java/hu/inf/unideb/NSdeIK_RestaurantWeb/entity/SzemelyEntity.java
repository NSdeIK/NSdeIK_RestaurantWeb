package hu.inf.unideb.NSdeIK_RestaurantWeb.entity;

import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@RedisHash("Szemelyek")
public class SzemelyEntity {
    @Id
    private String szemely_id;

    @Indexed @NonNull
    private String szemely_nev;

    @Indexed
    private SzemelyPosztok szemely_poszt;
}
