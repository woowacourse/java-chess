package domain.piece;

import domain.Board;
import domain.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @DisplayName("룩이 이동하려는 위치가 상하좌우 범위라면, 성공을 반환한다.(빈칸인 경우)")
    @Test
    void check_row_column_range_true_test() {
        Board board = new Board(PieceFactory.createPieces());
        Rook rook = Rook.Of("R", Position.Of(3, 3), true);

        assertThat(rook.canMove(board.getBoard(), Position.Of(2, 3))).isTrue();
        assertThat(rook.canMove(board.getBoard(), Position.Of(4, 3))).isTrue();
        assertThat(rook.canMove(board.getBoard(), Position.Of(3, 2))).isTrue();
        assertThat(rook.canMove(board.getBoard(), Position.Of(3, 4))).isTrue();
    }

    @DisplayName("룩이 이동하려는 위치에 같은 편 말이 있는 경우 이동할 수 없다.")
    @Test
    void cant_move_rook_if_same_color_piece_exists() {
        Board board = new Board(PieceFactory.createPieces());
        Rook rook = Rook.Of("R", Position.Of(2, 3), true);
        assertThat(rook.canMove(board.getBoard(), Position.Of(1, 3))).isFalse();
    }

    @DisplayName("룩이 이동하려는 위치가 상하좌우 범위가 아니라면, 실패를 반환한다.")
    @Test
    void check_row_column_range_false_test() {
        Board board = new Board(PieceFactory.createPieces());
        Rook rook = Rook.Of("R", Position.Of(3, 3), true);

        assertThat(rook.canMove(board.getBoard(), Position.Of(4, 4))).isFalse();
        assertThat(rook.canMove(board.getBoard(), Position.Of(2, 2))).isFalse();
    }

    @DisplayName("룩이 이동하려는 경로에 다른 말이 있으면, 실패를 반환한다.")
    @Test
    void cant_move_rook_if_piece_exist() {
        Board board = new Board(PieceFactory.createPieces());
        Rook rook = Rook.Of("R", Position.Of(3, 3), true);
        Pawn pawn = Pawn.Of("P", Position.Of(3, 4), true);
        board.put(pawn, Position.Of(3, 4));
        assertThat(rook.canMove(board.getBoard(), Position.Of(3, 5))).isFalse();
    }
}