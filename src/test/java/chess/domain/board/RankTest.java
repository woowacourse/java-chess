package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("Rank 열거 객체의 value를 비교한다")
    @Test
    void compareRankValue() {
        Rank rank = Rank.FIVE;

        assertThat(rank.getValue()).isEqualTo(5);
    }
}