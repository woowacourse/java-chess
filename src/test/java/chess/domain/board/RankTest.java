package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @Test
    @DisplayName("한칸 위의 Rank를 반환한다")
    void rankPlusTest() {
        final var rank = Rank.FIVE.plus();

        assertThat(rank).isEqualTo(Rank.SIX);
    }

    @Test
    @DisplayName("한칸 아래의 Rank를 반환한다")
    void rankMinusTest() {
        final var rank = Rank.FIVE.minus();

        assertThat(rank).isEqualTo(Rank.FOUR);
    }

    @Test
    @DisplayName("범위를 벗어난 랭크로 이동하면 예외가 발생한다")
    void rankMinusExceptionTest() {
        assertThatThrownBy(ONE::minus)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위를 벗어난 랭크로 이동하면 예외가 발생한다")
    void rankPlusExceptionTest() {
        assertThatThrownBy(EIGHT::plus)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
