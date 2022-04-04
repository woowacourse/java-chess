package chess.chessboard.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.chessboard.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("Rank을 이동한다.")
    @Test
    void add() {
        assertThat(ONE.add(1)).isEqualTo(TWO);
    }

    @DisplayName("Rank를 이동할 수 없는 경우 false를 반환한다.")
    @Test
    void canAdd_false() {
        assertThat(ONE.canAdd(-1)).isFalse();
    }
}