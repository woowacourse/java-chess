package chess;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        chessBoard.initBoard();
    }

    @ParameterizedTest
    @DisplayName("체스 초기 기물 배치 확인")
    @CsvSource(value = {"0, 4, K", "7, 4, k", "1, 0, P", "6, 0, p", "5, 0, ."}, delimiter = ',')
    void pieceLocationCheck(int i, int j, String value) {
        assertThat(chessBoard.getSquare(Position.of(i,j)).getName()).isEqualTo(value);
    }

    @Test
    void movePawnSuccess() {
        chessBoard.move("b2", "b3");
        assertThat(chessBoard.getSquare(Position.of(5, 1)).getName()).isEqualTo("p");
    }

    @Test
    void movePawnStart() {
        chessBoard.move("b2", "b4");
        assertThat(chessBoard.getSquare(Position.of(4, 1)).getName()).isEqualTo("p");
    }

    @Test
    void attackPawnSuccess() {
        chessBoard.move("c7", "c5");
        chessBoard.move("c5", "c4");
        chessBoard.move("c4", "c3");
        chessBoard.move("b2", "c3");
        assertThat(chessBoard.getSquare(Position.of(5, 2)).getName()).isEqualTo("p");
    }

    @Test
    void attackPawnFail() {
        chessBoard.move("c7", "c5");
        chessBoard.move("c5", "c4");
        chessBoard.move("c4", "c3");
        assertThatThrownBy(() -> {
            chessBoard.move("c2", "c3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void movePawnFailLinear() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b1");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void movePawnFailDiagonal() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "c3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void movePawnFailSame() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveRookSuccess() {
        chessBoard.move("a2", "a3");
        chessBoard.move("a1", "a2");
        assertThat(chessBoard.getSquare(Position.of(6, 0)).getName()).isEqualTo("r");
    }

    @Test
    void moveRookFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("a1", "a2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void attackRookSuccess() {
        chessBoard.move("a2", "a4");
        chessBoard.move("a1", "a3");
        chessBoard.move("a3", "b3");
        chessBoard.move("b3", "b7");
        assertThat(chessBoard.getSquare(Position.of(1, 1)).getName()).isEqualTo("r");
    }

    @Test
    void moveBishopSuccess() {
        chessBoard.move("b2", "b3");
        chessBoard.move("c1", "b2");
        assertThat(chessBoard.getSquare(Position.of(6, 1)).getName()).isEqualTo("b");
    }

    @Test
    void moveBishopFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("c1", "b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void attackBishopSuccess() {
        chessBoard.move("b2", "b3");
        chessBoard.move("c1", "a3");
        chessBoard.move("a3", "e7");
        assertThat(chessBoard.getSquare(Position.of(1, 4)).getName()).isEqualTo("b");
    }

    @Test
    void moveKnightSuccess() {
        chessBoard.move("b1", "a3");
        assertThat(chessBoard.getSquare(Position.of(5, 0)).getName()).isEqualTo("n");
    }

    @Test
    void moveKnightFail() {
        assertThatThrownBy(() -> {
            chessBoard.move("b1", "d2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void scoreTest() {
        chessBoard.move("b2", "b4");
        chessBoard.move("c7", "c5");
        chessBoard.move("b4", "c5");
        assertThat(chessBoard.getScore(Color.WHITE)).isEqualTo(37.0);
        assertThat(chessBoard.getScore(Color.BLACK)).isEqualTo(37.0);
    }

    @Test
    void scoreTest2() {
        ChessBoard emptyChessBoard = new ChessBoard();
        emptyChessBoard.getSquare(Position.of("b8")).replacePiece(new King(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("c8")).replacePiece(new Rook(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("a7")).replacePiece(new Pawn(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("c7")).replacePiece(new Pawn(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("d7")).replacePiece(new Bishop(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("b6")).replacePiece(new Pawn(Color.BLACK));
        emptyChessBoard.getSquare(Position.of("e6")).replacePiece(new Queen(Color.BLACK));

        emptyChessBoard.getSquare(Position.of("f4")).replacePiece(new Knight(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("g4")).replacePiece(new Queen(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("f3")).replacePiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("h3")).replacePiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("f2")).replacePiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("g2")).replacePiece(new Pawn(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("e1")).replacePiece(new Rook(Color.WHITE));
        emptyChessBoard.getSquare(Position.of("f1")).replacePiece(new King(Color.WHITE));

        assertThat(emptyChessBoard.getScore(Color.BLACK)).isEqualTo(20.0);
        assertThat(emptyChessBoard.getScore(Color.WHITE)).isEqualTo(19.5);
    }
}
