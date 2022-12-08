package hu.inf.unideb.NSdeIK_RestaurantWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.inf.unideb.NSdeIK_RestaurantWeb.enums.SzemelyPosztok;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@RedisHash("Asztalok")
public class AsztalEntity {
    @Id
    private String asztal_id;

    @Indexed
    private String asztal_nev;

    @Indexed
    private Integer maxfo;

    @Indexed
    private String statusz;
}
