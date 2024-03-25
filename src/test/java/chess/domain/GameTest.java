package chess.domain;

import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A6;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    /*
    RNBQKBNR  8
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    ........  3
    pppppppp  2
    rnbqkbnr  1

    abcdefgh
     */
    @DisplayName("검은색 진영이 먼저 턴을 진행하면 예외가 발생한다.")
    @Test
    void notProceedBlackTurn() {
        Game game = new Game();

        assertThatThrownBy(() -> game.proceedTurn(A7, A6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 진영의 차례가 아닙니다.");
    }

    @DisplayName("흰색 진영이 먼저 턴을 진행한다.")
    @Test
    void proceedWhiteTurn() {
        Game game = new Game();

        assertThatCode(() -> game.proceedTurn(A2, A3))
                .doesNotThrowAnyException();
    }

    /*
    RNBQKBNR  8
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    p.......  3
    .ppppppp  2
    rnbqkbnr  1

    abcdefgh
     */
    @DisplayName("흰색 진영 다음에 흰색 진영이 턴을 진행하면 예외가 발생한다.")
    @Test
    void notProceedWhiteTurn() {
        Game game = new Game();
        game.proceedTurn(A2, A3);

        assertThatThrownBy(() -> game.proceedTurn(B2, B3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 진영의 차례가 아닙니다.");
    }

    @DisplayName("흰색 진영 다음에 검은색 진영이 턴을 진행한다.")
    @Test
    void proceedBlackTurn() {
        Game game = new Game();
        game.proceedTurn(A2, A3);

        assertThatCode(() -> game.proceedTurn(A7, A6))
                .doesNotThrowAnyException();
    }
}
