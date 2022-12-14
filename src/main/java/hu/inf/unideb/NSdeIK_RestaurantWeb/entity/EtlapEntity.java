package hu.inf.unideb.NSdeIK_RestaurantWeb.entity;

import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.EtlapTipusok;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@RedisHash("Etlap")
public class EtlapEntity {
    @Id
    private String etlap_id;

    @Indexed
    private EtlapTipusok etlap_tipus;

    @Indexed
    private String etlap_neve;

    @Indexed
    private Integer etlap_ara;
}
