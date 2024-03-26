package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @DisplayName("존재하지 않는 Rank 좌표로 조회할 경우 예외가 발생한다.")
    void fromWithInvalidCoordinate() {
        // given
        int coordinate = 0;

        // when & then
        assertThatThrownBy(() -> Rank.from(coordinate))
                .isInstanceOf(IllegalArgumentException.class);
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
    @DisplayName("오프셋으로 다음 Rank를 구할 때 해당 좌표의 Rank가 없다면 예외가 발생한다.")
    void calculateNextRankWhenNotExist() {
        // given
        Rank rank = Rank.EIGHT;
        int offset = 1;

        // when & then
        assertThatThrownBy(() -> rank.calculateNextRank(offset))
                .isInstanceOf(IllegalArgumentException.class);
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
