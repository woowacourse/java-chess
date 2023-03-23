package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("from은 값에 맞는 Rank 객체를 반환해준다.")
    void test_from() {
        final int value = 5;
        final Rank rank = Rank.from(value);
        assertSame(rank, Rank.FIVE);
    }

    @Test
    @DisplayName("유효하지 않는 수로 from을 호출시 예외처리 한다.")
    void test_fromThrowException() {
        final int unvalidatedValue = 9;

        assertThatThrownBy(() -> Rank.from(unvalidatedValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Rank.RANK_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("Rank를 받아 두 수의 차를 반환한다.")
    void test_calculateRankGap() {
        int[] rankValues = {1, 5};
        final Rank value = Rank.from(rankValues[0]);
        final Rank subtrahend = Rank.from(rankValues[1]);

        final int result = value.calculateRankGap(subtrahend);

        assertThat(result)
                .isEqualTo(rankValues[0] - rankValues[1]);
    }

    @Test
    @DisplayName("값을 더한 Rank를 반환한다.")
    void test_addValue() {
        final int addition = 1;
        final Rank two = Rank.TWO;

        assertSame(two.addValue(addition), Rank.THREE);
    }
}
