package chess.domain.turn;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.single.King;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTurnTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = Position.of('a', '8');
    }

    @Test
    @DisplayName("chess board가 promotion 상태라면 현재 참조값을 반환")
    void nextTurnPromotion() {
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new Piece(WHITE, new Pawn(WHITE))));
        GameTurn gameTurn = new RunningTurn(chessBoard, WHITE);

        assertThat(gameTurn).isSameAs(gameTurn.nextTurn());
    }

    @Test
    @DisplayName("게임이 종료되었으면 종료 상태를 반환")
    void nextTurnEnd() {
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new Piece(WHITE, new King())));
        GameTurn gameTurn = new RunningTurn(chessBoard, WHITE);

        assertThat(gameTurn.nextTurn()).isInstanceOf(EndTurn.class);
    }
}
