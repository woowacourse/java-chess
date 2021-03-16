package domain.chess.piece;

import domain.chess.Board;
import domain.chess.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("Pawn은 전진 1칸 또는 2칸 이동만 가능하다.")
    @Test
    void pawn_move_forward() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(1, 0), true);
        assertThat(pawn.canMove(board.getBoard(), Position.Of(2, 0))).isEqualTo(true);
        assertThat(pawn.canMove(board.getBoard(), Position.Of(3, 0))).isEqualTo(true);
        assertThat(pawn.canMove(board.getBoard(), Position.Of(3, 2))).isEqualTo(false);
    }

    @DisplayName("Pawn의 대각선은 상대의 말이 있을 경우만 가능하다.")
    @Test
    void catch_enemy() {
    }
}