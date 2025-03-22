package chess.board;

import static chess.Fixtures.A1;
import static chess.Fixtures.A2;
import static chess.Fixtures.A3;
import static chess.Fixtures.generalBoard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test 위치 조정 TODO
 */
class BoardTest {
    @DisplayName("중간에_다른_기물이_존재하면_예외를_발생한다")
    @Test
    void move1() {
        //when
        //then
        Assertions.assertThatThrownBy(() -> generalBoard.move(A1, A3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중간에 다른 기물을 뛰어넘을 수 없습니다.");
    }

    @DisplayName("도착지에_같은_색깔의_말이_존재하면_예외를_발생한다")
    @Test
    void move2() {
        //when
        //then
        Assertions.assertThatThrownBy(() -> generalBoard.move(A1, A2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대편의 말만 잡을 수 있습니다.");
    }
}
