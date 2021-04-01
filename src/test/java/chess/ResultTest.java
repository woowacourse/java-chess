package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Result;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        Map<Position, Piece> board = chessBoard.getChessBoard();
        board.put(Position.of("b8"), new King(Color.BLACK));
        board.put(Position.of("c8"), (new Rook(Color.BLACK)));
        board.put(Position.of("a7"), (new Pawn(Color.BLACK)));
        board.put(Position.of("c7"), (new Pawn(Color.BLACK)));
        board.put(Position.of("d7"), (new Bishop(Color.BLACK)));
        board.put(Position.of("b6"), (new Pawn(Color.BLACK)));
        board.put(Position.of("e6"), (new Queen(Color.BLACK)));

        board.put(Position.of("f4"), (new Knight(Color.WHITE)));
        board.put(Position.of("g4"), (new Queen(Color.WHITE)));
        board.put(Position.of("f3"), (new Pawn(Color.WHITE)));
        board.put(Position.of("h3"), (new Pawn(Color.WHITE)));
        board.put(Position.of("f2"), (new Pawn(Color.WHITE)));
        board.put(Position.of("g2"), (new Pawn(Color.WHITE)));
        board.put(Position.of("e1"), (new Rook(Color.WHITE)));
        board.put(Position.of("f1"), (new King(Color.WHITE)));
    }

    @Test
    @DisplayName("각 진영 별 점수 계산")
    void scoreTest() {
        Result result = new Result();
        result.add(Color.WHITE, chessBoard.score(Color.WHITE));
        result.add(Color.BLACK, chessBoard.score(Color.BLACK));
        assertThat(result.getScore(Color.WHITE)).isEqualTo(19.5);
        assertThat(result.getScore(Color.BLACK)).isEqualTo(20.0);
    }

    @Test
    @DisplayName("각 진영 별 승패 판별")
    void winOrLose() {
        Result result = new Result();
        result.add(Color.WHITE, chessBoard.score(Color.WHITE));
        result.add(Color.BLACK, chessBoard.score(Color.BLACK));
        assertThat(result.winOrLose(Color.WHITE)).isEqualTo("패");
        assertThat(result.winOrLose(Color.BLACK)).isEqualTo("승");
    }
}
