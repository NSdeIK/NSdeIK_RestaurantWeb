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
@RedisHash("LefoglaltAsztalok")
public class AsztalLefoglalEntity {
    @Id
    private String asztal_id;

    @Indexed
    private String kezelo_szemely_id;

    @Indexed
    private Integer vendegek_szama;
}
