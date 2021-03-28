package domain.piece.objects;

import domain.Board;
import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @DisplayName("룩이 이동하려는 위치가 상하좌우 범위라면, 성공을 반환한다.(빈칸인 경우)")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "d4", "c5", "e5"})
    void check_row_column_range_true_test(String endPosition) {
        Rook rook = Rook.of("R", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("d5"), rook);
        }});
        assertThat(rook.canMove(board.getBoard(), Position.of("d5"), Position.of(endPosition))).isTrue();
    }

    @DisplayName("룩이 이동하려는 위치에 같은 편 말이 있는 경우 이동할 수 없다.")
    @Test
    void cant_move_rook_if_same_color_piece_exists() {
        Rook rook = Rook.of("R", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("d6"), rook);
            put(Position.of("d7"), Queen.of("Q", true));
        }});
        assertThat(rook.canMove(board.getBoard(), Position.of("d5"), Position.of("d7"))).isFalse();
    }

    @DisplayName("룩이 이동하려는 위치가 상하좌우 범위가 아니라면, 실패를 반환한다.")
    @Test
    void check_row_column_range_false_test() {
        Rook rook = Rook.of("R", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("d5"), rook);
        }});
        assertThat(rook.canMove(board.getBoard(), Position.of("d5"), Position.of("e4"))).isFalse();
        assertThat(rook.canMove(board.getBoard(), Position.of("d5"), Position.of("c6"))).isFalse();
    }

    @DisplayName("룩이 이동하려는 경로에 다른 말이 있으면, 실패를 반환한다.")
    @Test
    void cant_move_rook_if_piece_exist() {
        Rook rook = Rook.of("R", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("d5"), rook);
            put(Position.of("e5"), Pawn.of("P", true));
        }});
        assertThat(rook.canMove(board.getBoard(), Position.of("d5"), Position.of("f5"))).isFalse();
    }
}