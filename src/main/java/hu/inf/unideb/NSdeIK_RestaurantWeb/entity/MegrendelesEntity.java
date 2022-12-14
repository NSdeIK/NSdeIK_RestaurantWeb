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
@RedisHash("Megrendelesek")
public class MegrendelesEntity {
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
    private Integer megrendeles_ara;

    @Indexed
    private String szakacs;
}
