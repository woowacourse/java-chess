package chess.domain.turn;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTurnTest {

    @Test
    @DisplayName("EndTurn에서 nextTurn 호출 시 예외발생")
    void nextTurnException() {
        GameTurn endTurn = new EndTurn(new ChessBoard(new HashMap<>()));
        assertThatThrownBy(() -> endTurn.nextTurn())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 게임은 다음 턴이 없습니다.");
    }
}
