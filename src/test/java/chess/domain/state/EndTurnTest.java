package chess.domain.state;

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
        ChessGameState endTurn = new EndState(new ChessBoard(new HashMap<>()));
        assertThatThrownBy(() -> endTurn.nextTurn())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 게임은 다음 턴이 없습니다.");
    }

    @Test
    @DisplayName("EndTurn에서 movePiece 호출 시 예외발생")
    void movePieceException() {
        ChessGameState endTurn = new EndState(new ChessBoard(new HashMap<>()));
        assertThatThrownBy(() -> endTurn.movePiece(Position.of('a', '1'), Position.of('a', '2')))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 게임은 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("EndTurn에서 promotion 호출 시 예외발생")
    void promotionException() {
        ChessGameState endTurn = new EndState(new ChessBoard(new HashMap<>()));
        assertThatThrownBy(() -> endTurn.promotion(PromotionPiece.BISHOP))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 게임은 promotion할 수 없습니다.");
    }

    @Test
    @DisplayName("EndTurn에서 currentScore 호출 시 예외발생")
    void currentScoreException() {
        ChessGameState endTurn = new EndState(new ChessBoard(new HashMap<>()));
        assertThatThrownBy(() -> endTurn.currentScore())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 게임은 score를 계산할 수 없습니다.");
    }
}
