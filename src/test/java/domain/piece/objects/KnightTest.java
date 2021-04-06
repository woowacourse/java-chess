package domain.piece.objects;

import domain.Board;
import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("knight은 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 있다.(빈칸일 경우)")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "c5", "f6", "g5", "c3", "d2", "f2", "g3"})
    void knight_move_if_empty_piece(String endPosition) {
        Knight knight = Knight.of("N", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e4"), knight);
        }});
        assertThat(knight.canMove(board.getPieceMap(), Position.of("e4"), Position.of(endPosition))).isTrue();
    }

    @DisplayName("knight은 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 있다.(다른 색의 기물일 경우)")
    @Test
    void knight_move_if_different_color_piece() {
        Knight knight = Knight.of("N", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e4"), knight);
            put(Position.of("d2"), Pawn.of("p", false));
            put(Position.of("f2"), Queen.of("q", false));
        }});
        assertThat(knight.canMove(board.getPieceMap(), Position.of("e4"), Position.of("d2"))).isTrue();
        assertThat(knight.canMove(board.getPieceMap(), Position.of("e4"), Position.of("f2"))).isTrue();
    }

    @DisplayName("knight은 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 없다.(같은 색의 기물일 경우)")
    @Test
    void knight_move_if_same_color_piece() {
        Knight knight = Knight.of("N", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e4"), knight);
            put(Position.of("d2"), Pawn.of("P", true));
            put(Position.of("f2"), Queen.of("Q", true));
        }});
        assertThat(knight.canMove(board.getPieceMap(), Position.of("e4"), Position.of("d2"))).isFalse();
        assertThat(knight.canMove(board.getPieceMap(), Position.of("e4"), Position.of("f2"))).isFalse();
    }

    @DisplayName("knight의 이동 가능 범위 외의 위치가 목적지인 경우 이동할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "f3", "e5", "e4"})
    void cant_move_knight_test(String endPoint) {
        Knight knight = Knight.of("N", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e4"), knight);
        }});
        assertThat(knight.canMove(board.getPieceMap(), Position.of("e4"), Position.of(endPoint))).isFalse();
    }
}