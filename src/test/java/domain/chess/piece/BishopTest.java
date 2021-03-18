package domain.chess.piece;

import domain.chess.Board;
import domain.chess.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("비숍이 대각선으로 이동하는 지 검사한다.(목적지가 빈칸일 경우)")
    @Test
    void check_diagonal_if_empty_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Bishop bishop = Bishop.Of("B", Position.Of(3, 3), true);

        assertThat(bishop.canMove(board.getBoard(), Position.Of(4, 4))).isTrue();
        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 2))).isTrue();

        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 4))).isTrue();
        assertThat(bishop.canMove(board.getBoard(), Position.Of(4, 2))).isTrue();
    }

    @DisplayName("비숍이 대각선으로 이동하는 지 검사한다.(목적지에 적 기물이 있을 경우)")
    @Test
    void check_diagonal_if_enemy_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Bishop bishop = Bishop.Of("B", Position.Of(3, 3), true);
        Pawn pawn = Pawn.Of("P", Position.Of(4, 4), true);

        board.put(pawn, Position.Of(4,4));
        assertThat(bishop.canMove(board.getBoard(), Position.Of(4, 4))).isFalse();
    }

    @DisplayName("비숍이 이동하려는 위치가 대각선이 아니면 실패를 반환한다.")
    @Test
    void check_diagonal_false_test() {
        Board board = new Board(PieceFactory.createPieces());
        Bishop bishop = Bishop.Of("B", Position.Of(3, 3), true);
        assertThat(bishop.canMove(board.getBoard(), Position.Of(3, 4))).isFalse();
        assertThat(bishop.canMove(board.getBoard(), Position.Of(3, 2))).isFalse();

        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 3))).isFalse();
        assertThat(bishop.canMove(board.getBoard(), Position.Of(4, 3))).isFalse();

        assertThat(bishop.canMove(board.getBoard(), Position.Of(7, 4))).isFalse();
        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 5))).isFalse();
    }

    @DisplayName("이동 경로 중간에 기물이 존재하면, 이동할 수 없다.")
    @Test
    void cant_move_bishop_if_piece_exist() {
        Board board = new Board(PieceFactory.createPieces());

        Bishop bishop = Bishop.Of("B", Position.Of(4, 4), true);
        Pawn pawn = Pawn.Of("P", Position.Of(3, 3), true);
        board.put(pawn, Position.Of(3, 3));
        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 2))).isFalse();
    }

    @DisplayName("이동할 경로에 같은 편 말이 존재할 경우 이동할 수 없다.")
    @Test
    void cant_move_bishop_if_same_color_piece_exist() {
        Board board = new Board(PieceFactory.createPieces());

        Bishop bishop = Bishop.Of("B", Position.Of(4, 4), true);
        Pawn pawn = Pawn.Of("P", Position.Of(3, 3), true);
        board.put(pawn, Position.Of(3, 3));
        assertThat(bishop.canMove(board.getBoard(), Position.Of(3, 3))).isFalse();
    }
}