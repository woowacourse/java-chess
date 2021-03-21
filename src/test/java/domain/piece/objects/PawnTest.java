package domain.piece.objects;

import domain.Board;
import domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("Pawn은 한 칸 이동이 가능하다.(빈 칸일 경우)")
    @Test
    void pawn_move_forward_if_empty_piece() {
        Pawn pawn = Pawn.of("P", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("a7"), pawn);
        }});
        assertThat(pawn.canMove(board.getBoard(), Position.of("a7"), Position.of("a6"))).isTrue();
    }

    @DisplayName("Pawn은 한 칸 이동이 불가능하다.(같은 편 기물이 있는 경우)")
    @Test
    void pawn_move_forward_if_same_color_piece() {
        Pawn pawn = Pawn.of("P", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("a7"), pawn);
            put(Position.of("a6"), Rook.of("R", true));
        }});
        assertThat(pawn.canMove(board.getBoard(), Position.of("a7"), Position.of("a6"))).isFalse();
    }

    @DisplayName("Pawn은 최초 위치에서만 2칸 이동이 가능하다.(이동 하려는 위치가 빈 칸일 경우)")
    @Test
    void pawn_move_forward_two_step_if_empty_piece() {
        Pawn pawn = Pawn.of("P", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("a7"), pawn);
        }});
        assertThat(pawn.canMove(board.getBoard(), Position.of("a7"), Position.of("a5"))).isTrue();
    }

    @DisplayName("목적지에 기물이 있는 경우(색깔 구분 없음) Pawn은 2칸 이동이 불가능하다.)")
    @Test
    void pawn_cant_move_forward_two_step_if_piece() {
        Pawn pawn = Pawn.of("P", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("a7"), pawn);
            put(Position.of("a5"), Rook.of("r", false));
        }});
        assertThat(pawn.canMove(board.getBoard(), Position.of("a7"), Position.of("a5"))).isFalse();
    }

    @DisplayName("Pawn은 최초 위치가 아닐경우 2칸 이동이 불가능하다.")
    @Test
    void pawn_cant_move_forward_two_step_if_not_initial_position() {
        Pawn pawn = Pawn.of("P", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("a6"), pawn);
        }});
        assertThat(pawn.canMove(board.getBoard(), Position.of("a6"), Position.of("a4"))).isFalse();
    }

    @DisplayName("Pawn의 전방 대각선에 적 기물이 있는 경우만 이동 가능하다.(빈칸은 이동 불가)")
    @Test
    void catch_enemy_if_enemy_exist_at_diagonal() {
        Pawn pawn = Pawn.of("P", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("c6"), pawn);
            put(Position.of("d5"), King.of("k", false));
        }});
        assertThat(pawn.canMove(board.getBoard(), Position.of("c6"), Position.of("d5"))).isTrue();
    }

    @DisplayName("Pawn의 전방 대각선에 적 기물이 없는 경우는 이동 불가능하다.")
    @Test
    void catch_enemy_if_enemy_not_exist_at_diagonal() {
        Pawn pawn = Pawn.of("P", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("c6"), pawn);
        }});
        assertThat(pawn.canMove(board.getBoard(), Position.of("c6"), Position.of("b5"))).isFalse();
    }
}