package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("좌표 값으로 Rank를 구한다.")
    void from() {
        // given
        int coordinate = 3;

        // when
        Rank actualRank = Rank.from(coordinate);

        // then
        assertThat(actualRank).isEqualTo(Rank.THREE);
    }

    @Test
    @DisplayName("오프셋으로 다음 Rank를 구한다.")
    void calculateNextRank() {
        // given
        Rank rank = Rank.FIVE;
        int offset = 2;

        // when
        Rank actualRank = rank.calculateNextRank(offset);

        // then
        assertThat(actualRank).isEqualTo(Rank.SEVEN);
    }

    @Test
    @DisplayName("Rank의 차이(Difference)를 계산한다.")
    void minus() {
        // given
        Rank target = Rank.ONE;
        Rank other = Rank.FIVE;

        // when
        Difference difference = target.minus(other);

        // then
        assertThat(difference).isEqualTo(Difference.from(-4));
    }
}
