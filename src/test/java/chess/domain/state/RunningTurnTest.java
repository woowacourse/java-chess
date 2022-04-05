package chess.domain.state;

import static chess.domain.Color.WHITE;
import static chess.domain.state.Turn.BLACK_TURN;
import static chess.domain.state.Turn.END;
import static chess.domain.state.Turn.WHITE_TURN;
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
    @DisplayName("chess board가 promotion 상태라면 턴을 반환")
    void nextTurnPromotion() {
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new Piece(WHITE, new Pawn(WHITE))));
        ChessGameState chessGameState = new RunningState(chessBoard, WHITE);

        assertThat(chessGameState.nextTurn()).isEqualTo(WHITE_TURN);
    }

    @Test
    @DisplayName("게임이 종료되었으면 종료 상태를 반환")
    void nextTurnEnd() {
        ChessBoard chessBoard = new ChessBoard(Map.of(position, new Piece(WHITE, new King())));
        ChessGameState chessGameState = new RunningState(chessBoard, WHITE);

        assertThat(chessGameState.nextTurn()).isEqualTo(END);
    }

    @Test
    @DisplayName("현재턴 종료 시 다음 턴 반환")
    void nextTurnReverseColor() {
        ChessBoard chessBoard = new ChessBoard(Map.of(Position.of('a', '1'), new Piece(WHITE, new Pawn(WHITE))));
        ChessGameState chessGameState = new RunningState(chessBoard, WHITE);

        assertThat(chessGameState.nextTurn()).isEqualTo(BLACK_TURN);
    }
}
