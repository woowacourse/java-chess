package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("Rank 열거 객체의 value를 비교한다")
    @Test
    void compareRankValue() {
        Rank rank = Rank.FIVE;

        assertThat(rank.getY()).isEqualTo(5);
    }
}
