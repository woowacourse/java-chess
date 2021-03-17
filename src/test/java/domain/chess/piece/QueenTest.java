package domain.chess.piece;

import domain.chess.Board;
import domain.chess.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @DisplayName("Queen은 전후좌우, 대각선으로 칸수 제한없이 움직일 수 있다.")
    @Test
    void queen_move_test() {
        Board board = new Board(PieceFactory.createPieces());
        Queen queen = Queen.Of("Q", Position.Of(4, 4), true);

        assertThat(queen.canMove(board.getBoard(), Position.Of(3, 3))).isTrue();
        assertThat(queen.canMove(board.getBoard(), Position.Of(2, 4))).isTrue();
        assertThat(queen.canMove(board.getBoard(), Position.Of(5, 5))).isTrue();
        assertThat(queen.canMove(board.getBoard(), Position.Of(6, 4))).isTrue();
        assertThat(queen.canMove(board.getBoard(), Position.Of(3, 5))).isTrue();
        assertThat(queen.canMove(board.getBoard(), Position.Of(4, 2))).isTrue();
        assertThat(queen.canMove(board.getBoard(), Position.Of(5, 3))).isTrue();
        assertThat(queen.canMove(board.getBoard(), Position.Of(4, 6))).isTrue();
    }

    @DisplayName("Queen은 전후좌우, 대각선 이외의 위치로 움직일 수 없다.")
    @Test
    void queen_move_fail_test() {
        Board board = new Board(PieceFactory.createPieces());
        Queen queen = Queen.Of("Q", Position.Of(4, 4), true);

        assertThat(queen.canMove(board.getBoard(), Position.Of(2, 3))).isFalse();
        assertThat(queen.canMove(board.getBoard(), Position.Of(6, 5))).isFalse();
        assertThat(queen.canMove(board.getBoard(), Position.Of(6, 3))).isFalse();
        assertThat(queen.canMove(board.getBoard(), Position.Of(2, 5))).isFalse();
        assertThat(queen.canMove(board.getBoard(), Position.Of(3, 2))).isFalse();
        assertThat(queen.canMove(board.getBoard(), Position.Of(5, 2))).isFalse();
        assertThat(queen.canMove(board.getBoard(), Position.Of(3, 6))).isFalse();
        assertThat(queen.canMove(board.getBoard(), Position.Of(5, 6))).isFalse();
    }

    @DisplayName("Queen이 이동하려는 경로에 다른 말이 있으면, 실패를 반환한다.")
    @Test
    void cant_move_queen_if_piece_exist() {
        Board board = new Board(PieceFactory.createPieces());
        Queen queen = Queen.Of("Q", Position.Of(4, 4), true);
        Pawn pawn = Pawn.Of("P", Position.Of(3, 4), true);
        board.put(pawn, Position.Of(3, 4));
        assertThat(queen.canMove(board.getBoard(), Position.Of(2, 4))).isFalse();

        Pawn pawn2 = Pawn.Of("P", Position.Of(5, 5), true);
        board.put(pawn2, Position.Of(5, 5));
        assertThat(queen.canMove(board.getBoard(), Position.Of(6, 6))).isFalse();
    }
}