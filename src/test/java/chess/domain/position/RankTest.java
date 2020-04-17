package chess.domain.position;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    void getRankDifferenceTest() {
        assertThat(Rank.EIGHT.getRankDifference(Rank.FIVE)).isEqualTo(-3);
    }
}
