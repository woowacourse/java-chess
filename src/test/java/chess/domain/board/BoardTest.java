package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @DisplayName("이동하려는 위치에 같은 팀 기물이 있으면 갈 수 없다")
    @Test
    void move_h1_h2() {
        //given
        Board board = new Board();
        Position h1 = Position.of(Column.H, Row.ONE);
        Position h2 = Position.of(Column.H, Row.TWO);
        //then
        assertThatThrownBy(() -> board.move(h1, h2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 팀 기물이 있는 위치로는 이동할 수 없습니다.");
    }

    @DisplayName("이동하려는 위치가 빈 칸이면 이동할 수 있다.")
    @Test
    void move_a2_a4() {
        //given
        Board board = new Board();
        Position a2 = Position.of(Column.A, Row.TWO);
        Position a4 = Position.of(Column.A, Row.FOUR);
        //then
        assertThatNoException().isThrownBy(() -> board.move(a2, a4));
    }

    @DisplayName("빈칸의 위치를 출발지로 둘 수 없다.")
    @Test
    void move_blank_exception() {
        //given
        Board board = new Board();
        Position a3 = Position.of(Column.A, Row.THREE);
        Position a4 = Position.of(Column.A, Row.FOUR);
        //then
        assertThatThrownBy(() -> board.move(a3, a4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 있는 기물이 없습니다.");
    }

    @DisplayName("경로에 기물이 있을 경우 움직일 수 없다.")
    @Test
    void move_obstacle_exception() {
        //given
        Board board = new Board();
        Position a1 = Position.of(Column.A, Row.ONE);
        Position a4 = Position.of(Column.A, Row.FOUR);
        //then
        assertThatThrownBy(() -> board.move(a1, a4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 있어 움직일 수 없습니다.");
    }
}
