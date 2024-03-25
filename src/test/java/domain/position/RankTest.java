package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("두 랭크 사이의 거리를 구한다.")
    void distance_Ranks() {
        Rank source = Rank.FIVE;
        Rank target = Rank.ONE;

        int distance = source.distance(target);

        assertThat(distance).isEqualTo(4);
    }

    @Test
    @DisplayName("두 랭크 사이의 모든 랭크를 반환한다.")
    void betweenRanks() {
        Rank source = Rank.ONE;
        Rank target = Rank.FIVE;

        List<Rank> ranks = source.betweenRanks(target);

        assertThat(ranks).containsOnly(Rank.TWO, Rank.THREE, Rank.FOUR);
    }
}
