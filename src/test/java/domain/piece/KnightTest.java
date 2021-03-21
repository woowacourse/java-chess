package domain.piece;

import domain.Board;
import domain.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("knight은 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 있다.(빈칸일 경우)")
    @Test
    void knight_move_if_empty_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Knight knight = Knight.Of("N", Position.Of(4, 4), true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(2, 3))).isEqualTo(true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(3, 2))).isEqualTo(true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(2, 5))).isEqualTo(true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(3, 6))).isEqualTo(true);

        assertThat(knight.canMove(board.getBoard(), Position.Of(5, 2))).isEqualTo(true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(6, 3))).isEqualTo(true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(6, 5))).isEqualTo(true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(5, 6))).isEqualTo(true);
    }

    @DisplayName("knight은 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 있다.(다른 색의 기물일 경우)")
    @Test
    void knight_move_if_different_color_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Knight knight = Knight.Of("N", Position.Of(4, 4), true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(6, 3))).isEqualTo(true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(6, 5))).isEqualTo(true);
    }

    @DisplayName("knight은 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 없다.(같은 색의 기물일 경우)")
    @Test
    void knight_move_if_same_color_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Knight knight = Knight.Of("N", Position.Of(3, 4), true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(1, 3))).isEqualTo(false);
        assertThat(knight.canMove(board.getBoard(), Position.Of(1, 5))).isEqualTo(false);
    }

    @DisplayName("knight의 이동 위치가 잘못된 경우 false가 반환된다.")
    @Test
    void cant_move_knight_test() {
        Board board = new Board(PieceFactory.createPieces());
        Knight knight = Knight.Of("N", Position.Of(4, 4), true);
        assertThat(knight.canMove(board.getBoard(), Position.Of(3, 3))).isFalse();
        assertThat(knight.canMove(board.getBoard(), Position.Of(5, 5))).isFalse();
        assertThat(knight.canMove(board.getBoard(), Position.Of(3, 4))).isFalse();
        assertThat(knight.canMove(board.getBoard(), Position.Of(4, 4))).isFalse();
    }
}