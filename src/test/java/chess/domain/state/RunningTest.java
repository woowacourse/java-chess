package chess.domain.state;

import chess.domain.board.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

    @DisplayName("BlackTurn은 끝나지 않은 상태이다")
    @Test
    void testBlackTurnIsFinished() {
        State blackTurn = new BlackTurn(Board.getInitializedInstance());
        Assertions.assertThat(blackTurn.isFinished()).isFalse();
    }

    @DisplayName("WhiteTurn은 끝나지 않은 상태이다")
    @Test
    void testWhiteTurnIsFinished() {
        State whiteTurn = new WhiteTurn(Board.getInitializedInstance());
        Assertions.assertThat(whiteTurn.isFinished()).isFalse();
    }
}
