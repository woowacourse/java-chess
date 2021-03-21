package domain.piece;

import domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("이동 방향이 대각선이고, 목적지가 빈칸인 경 목적지로 이동한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a7", "f8", "b4", "e3"})
    void check_diagonal_if_empty_piece(String endPosition) {
        Bishop bishop = Bishop.of("B", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("c5"), bishop);
        }});
        assertThat(bishop.canMove2(board.getBoard(), Position.of("c5"), Position.of(endPosition))).isTrue();
    }

    @DisplayName("이동 방향이 대각선이고, 목적지에 적 기물이 있을 경우 목적지로 이동한다.")
    @Test
    void check_diagonal_if_enemy_piece() {
        Bishop bishop = Bishop.of("B", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("c5"), bishop);
            put(Position.of("d4"), Pawn.of("p", false));
        }});
        assertThat(bishop.canMove2(board.getBoard(), Position.of("c5"), Position.of("d4"))).isTrue();
    }

    @DisplayName("비숍이 이동하려는 위치가 대각선이 아니면 실패를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c6", "a6", "e5", "h4", "c3"})
    void check_diagonal_false_test(String endPosition) {
        Bishop bishop = Bishop.of("B", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("c5"), bishop);
        }});
        assertThat(bishop.canMove2(board.getBoard(), Position.of("c5"), Position.of(endPosition))).isFalse();
    }

    @DisplayName("이동 경로 중간에 기물이 존재하면(색깔 구분 없음), 이동할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"c5:d4:P:true:e3", "e5:f6:p:false:h8", "e5:c3:P:true:b2", "e5:c7:p:false:b8"}, delimiter = ':')
    void cant_move_bishop_if_piece_exist(String from, String middle, String name, boolean color, String to) {
        Bishop bishop = Bishop.of("B", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of(from), bishop);
            put(Position.of(middle), Pawn.of(name, color));
        }});
        assertThat(bishop.canMove2(board.getBoard(), Position.of(from), Position.of(to))).isFalse();
    }

    @DisplayName("목적지에 같은 색깔의 기물이 있는 경우 이동할 수 없다.")
    @Test
    void cant_move_bishop_if_same_color_piece_exist() {
        Bishop bishop = Bishop.of("B", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e5"), bishop);
            put(Position.of("g3"), Pawn.of("P", true));
        }});
        assertThat(bishop.canMove2(board.getBoard(), Position.of("e5"), Position.of("g3"))).isFalse();
    }
}