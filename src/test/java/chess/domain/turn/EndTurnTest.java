package chess.domain.turn;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.PromotionPiece;
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

    @Test
    @DisplayName("EndTurn에서 movePiece 호출 시 예외발생")
    void movePieceException() {
        GameTurn endTurn = new EndTurn(new ChessBoard(new HashMap<>()));
        assertThatThrownBy(() -> endTurn.movePiece(Position.of('a', '1'), Position.of('a', '2')))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 게임은 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("EndTurn에서 promotion 호출 시 예외발생")
    void promotionException() {
        GameTurn endTurn = new EndTurn(new ChessBoard(new HashMap<>()));
        assertThatThrownBy(() -> endTurn.promotion(PromotionPiece.BISHOP))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 게임은 promotion할 수 없습니다.");
    }
}
