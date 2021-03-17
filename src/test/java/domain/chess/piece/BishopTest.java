package domain.chess.piece;

import domain.chess.Board;
import domain.chess.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @DisplayName("비숍이 대각선으로 이동하는 지 검사한다.")
    @Test
    void check_diagonal_test() {
        Board board = new Board(PieceFactory.createPieces());
        Bishop bishop = Bishop.Of("B", Position.Of(3, 3), true);

        assertThat(bishop.canMove(board.getBoard(), Position.Of(4, 4))).isTrue();
        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 2))).isTrue();

        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 4))).isTrue();
        assertThat(bishop.canMove(board.getBoard(), Position.Of(4, 2))).isTrue();
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

    @DisplayName("비숍이 이동하려는 경로에 다른 말이 있으면, 실패를 반환한다.")
    @Test
    void cant_move_bishop_if_piece_exist() {
        Board board = new Board(PieceFactory.createPieces());

        Bishop bishop = Bishop.Of("B", Position.Of(4, 4), true);
        Pawn pawn = Pawn.Of("P", Position.Of(3, 3), true);
        board.put(pawn, Position.Of(3, 3));
        assertThat(bishop.canMove(board.getBoard(), Position.Of(2, 2))).isFalse();
    }
}