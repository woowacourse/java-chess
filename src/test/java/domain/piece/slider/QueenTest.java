package domain.piece.slider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.ChessBoard;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

class QueenTest {

    @Test
    @DisplayName("queen이 대각선으로 이동할 수 있는 칸의 좌표를 반환한다.")
    void queenMoveDiagonalTest() {
        Queen queen = new Queen(Camp.WHITE);
        assertThat(queen.fetchMovableSquares(new Square(1, 3), new Square(5, 7))).contains(
                new Square(2, 4),
                new Square(3, 5),
                new Square(4, 6),
                new Square(5, 7)
        );
    }

    @Test
    @DisplayName("queen이 직선으로 이동할 수 있는 칸의 좌표를 반환한다.")
    void queenMoveForwardTest() {
        Queen queen = new Queen(Camp.WHITE);
        assertThat(queen.fetchMovableSquares(new Square(1, 3), new Square(4,3))).contains(
                new Square(2,3),
                new Square(3,3),
                new Square(4,3)
        );
    }

    @Test
    @DisplayName("targetSquare가 갈수없는 경로이면 예외를 던진다.")
    void bishopMoveFailTest() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();

        Queen queen = new Queen(Camp.WHITE);
        Map<Square, Piece> map = chessBoard.getBoard();
        assertThatThrownBy(() -> queen.canMove(map, new Square(2, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 경로입니다.");
    }
}
