package chess.position;

import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.FOUR;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.THREE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("Rank와 Rank간 거리를 계산할 수 있다.")
    void should_calculate_distance() {
        Rank rankOne = ONE;
        Rank rankTwo = Rank.TWO;

        assertThat(rankOne.calculateDifferenceTo(rankTwo)).isEqualTo(1);
    }

    @Test
    @DisplayName("rank와 rank간 증가하는 루트를 구할 수 있다")
    void should_find_incline_route() {
        Rank from = ONE;
        Rank to = EIGHT;

        List<Rank> rankRouteToTargetRank = from.findRankRouteToTargetRank(to);

        assertThat(rankRouteToTargetRank).containsOnly(TWO, THREE, FOUR, FIVE, SIX, SEVEN);
    }

    @Test
    @DisplayName("rank와 rank간 감소하는 루트를 구할 수 있다")
    void should_find_decline_route() {
        Rank from = EIGHT;
        Rank to = ONE;

        List<Rank> rankRouteToTargetRank = from.findRankRouteToTargetRank(to);

        assertThat(rankRouteToTargetRank).containsOnly(TWO, THREE, FOUR, FIVE, SIX, SEVEN);
    }
}
