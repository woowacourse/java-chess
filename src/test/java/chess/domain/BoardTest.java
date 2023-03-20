package chess.domain;

import static chess.domain.square.File.A;
import static chess.domain.square.File.B;
import static chess.domain.square.Rank.ONE;
import static chess.domain.square.Rank.THREE;
import static chess.domain.square.Rank.TWO;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("체스판을 생성한다.")
    void createBoard() {
        assertDoesNotThrow(Board::new);
    }

    @Test
    @DisplayName("보드가 생성되면 32개의 Piece를 가진다.")
    void containsPieces() {
        Board board = new Board();
        assertThat(board.getBoard()).hasSize(32);
    }

    @Test
    @DisplayName("말은 규칙에 따라 움직인다.")
    void move() {
        Board board = new Board();
        Square source = Square.of(A, TWO);
        Square target = Square.of(A, THREE);

        board.move(source, target);

        assertThat(board.getBoard().keySet()).contains(target);
    }

    @Test
    @DisplayName("knight는 가는 길목에 말이 있어도 움직일 수 있다.")
    void knight_can_move() {
        Board board = new Board();
        Square source = Square.of(B, ONE);
        Square target = Square.of(A, THREE);

        board.move(source, target);

        assertThat(board.getBoard().keySet()).contains(target);
    }

    @Test
    @DisplayName("knight는 이동할 칸에 말이 있으면 이동할 수 없다.")
    void knight_cannot_move() {
        Board board = new Board();
        Square source = Square.of(A, TWO);
        Square target = Square.of(A, THREE);
        board.move(source, target);

        Square source2 = Square.of(B, ONE);
        Square target2 = Square.of(A, THREE);

        assertThrows(IllegalArgumentException.class, () -> board.move(source2, target2));
    }

}
