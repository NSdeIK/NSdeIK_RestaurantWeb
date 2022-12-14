package hu.inf.unideb.NSdeIK_RestaurantWeb.entity;

import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.EtlapTipusok;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@RedisHash("MegrendelesVarolista")
public class MegrendelesVarolistaEntity {
    @Id
    private String id;

    @Indexed
    private String asztalid;

    @Indexed
    private String megrendeles_id;

    @Indexed
    private String megrendeles_neve;

    @Indexed
    private Integer megrendeles_db;

    @Indexed
    private String megrendeles_bekeresi_ido;
}
