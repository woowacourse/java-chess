package domain.piece.slider;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.ChessBoard;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

class RookTest {
    @Test
    @DisplayName("rook이 이동할 수 있는 칸의 좌표를 반환한다.")
    void RookMoveTest() {
        Rook rook = new Rook(Camp.WHITE);
        assertThat(rook.fetchMovableSquares(new Square(1, 3), new Square(4, 3))).contains(
            new Square(2, 3),
            new Square(3, 3),
            new Square(4, 3)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void RookMoveFailTest() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();

        Rook rook = new Rook(Camp.WHITE);
        Map<Square, Piece> map = chessBoard.getBoard();
        assertThatThrownBy(() -> rook.canMove(map, new Square(2, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("움직일 수 없는 경로입니다.");
    }
}
