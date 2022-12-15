package hu.inf.unideb.NSdeIK_RestaurantWeb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@RedisHash("Veglegesites")
public class VeglegesitesEntity {
    @Id
    private String id;
    @Indexed
    private String asztal_nev;
    @Indexed
    private String asztal_id;
    @Indexed
    private String kezelo_pincer_nev;
    @Indexed
    private Integer kifizetesi_osszeg;
    @Indexed
    private String mikor_veglegesitette;
}
