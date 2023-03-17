package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.move.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {

    @DisplayName("위로 한 칸 이동할 수 있다")
    @Test
    void up() {
        Rank rank = Rank.ONE;

        assertThat(rank.move(Direction.UP)).isEqualTo(Rank.TWO);
    }

    @DisplayName("아래로 한 칸 이동할 수 있다")
    @Test
    void down() {
        Rank rank = Rank.TWO;

        assertThat(rank.move(Direction.DOWN)).isEqualTo(Rank.ONE);
    }

    @DisplayName("좌우로 움직이면 이동하지 않는다")
    @ParameterizedTest
    @CsvSource({"LEFT", "RIGHT"})
    void moveLeftRight(Direction direction) {
        Rank rank = Rank.TWO;

        assertThat(rank.move(direction)).isEqualTo(Rank.TWO);
    }

    @DisplayName("가장 위에서 위로 더 이동할 수 없다")
    @Test
    void up_throws() {
        Rank rank = Rank.EIGHT;

        assertThatThrownBy(() -> rank.move(Direction.UP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }

    @DisplayName("가장 아래에서 아래로 더 이동할 수 없다")
    @Test
    void down_throws() {
        Rank rank = Rank.ONE;

        assertThatThrownBy(() -> rank.move(Direction.DOWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }

    @DisplayName("인덱스로 Rank를 찾을 수 있다")
    @Test
    void from() {
        Rank rank = Rank.from(1);

        assertThat(rank).isSameAs(Rank.ONE);
    }

    @DisplayName("잘못된 인덱스로 Rank를 찾으면 얘외가 발생한다")
    @Test
    void from_throws() {
        assertThatThrownBy(() -> Rank.from(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }
}
