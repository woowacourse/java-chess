package chess.domain.turn;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTurnTest {

    @Test
    @DisplayName("chess board가 promotion 상태라면 현재 참조값을 반환")
    void nextTurnPromotion() {
        ChessBoard chessBoard = new ChessBoard(Map.of(Position.of('a', '8'), new Piece(WHITE, new Pawn(WHITE))));
        GameTurn gameTurn = new RunningTurn(chessBoard, WHITE);

        assertThat(gameTurn).isSameAs(gameTurn.nextTurn());
    }
}
