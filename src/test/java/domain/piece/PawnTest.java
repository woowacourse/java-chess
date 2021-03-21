package domain.piece;

import domain.Board;
import domain.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("Pawn은 한 칸 이동이 가능하다.(빈 칸일 경우)")
    @Test
    void pawn_move_forward_if_empty_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(1, 0), true);
        assertThat(pawn.canMove(board.getBoard(), Position.Of(2, 0))).isEqualTo(true);
    }

    @DisplayName("Pawn은 한 칸 이동이 불가능하다.(같은 편 기물이 있는 경우)")
    @Test
    void pawn_move_forward_if_same_color_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(1, 0), true);
        board.put(Rook.Of("R", Position.Of(2, 0), true), Position.Of(2, 0));
        assertThat(pawn.canMove(board.getBoard(), Position.Of(2, 0))).isFalse();
    }

    @DisplayName("Pawn은 최초 위치에서만 2칸 이동이 가능하다.(이동 하려는 위치가 빈 칸일 경우)")
    @Test
    void pawn_move_forward_two_step_if_empty_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(1, 0), true);
        assertThat(pawn.canMove(board.getBoard(), Position.Of(3, 0))).isTrue();
    }

    @DisplayName("Pawn은 최초 위치에서만 2칸 이동이 불가능하다.(이동 하려는 위치가 빈 칸일 아닐 경우)")
    @Test
    void pawn_cant_move_forward_two_step_if_piece() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(1, 0), true);
        board.put(Rook.Of("R", Position.Of(3, 0), true), Position.Of(3, 0));

        assertThat(pawn.canMove(board.getBoard(), Position.Of(3, 0))).isFalse();
    }


    @DisplayName("Pawn은 최초 위치가 아닐경우 2칸 이동이 불가능하다.")
    @Test
    void pawn_cant_move_forward_two_step_if_not_initial_position() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(2, 0), true);
        board.put(pawn, Position.Of(2, 0));

        assertThat(pawn.canMove(board.getBoard(), Position.Of(4, 0))).isFalse();
    }

    @DisplayName("Pawn의 전방 대각선에 적 기물이 있는 경우만 이동 가능하다.")
    @Test
    void catch_enemy_if_enemy_exist_at_diagonal() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(1, 0), true);
        board.put(King.Of("k", Position.Of(2, 1), false), Position.Of(2, 1));
        assertThat(pawn.canMove(board.getBoard(), Position.Of(2, 1))).isTrue();
    }

    @DisplayName("Pawn의 전방 대각선에 적 기물이 없는 경우는 이동 불가능하다.")
    @Test
    void catch_enemy_if_enemy_not_exist_at_diagonal() {
        Board board = new Board(PieceFactory.createPieces());
        Pawn pawn = Pawn.Of("P", Position.Of(1, 0), true);
        assertThat(pawn.canMove(board.getBoard(), Position.Of(2, 1))).isFalse();
    }

}