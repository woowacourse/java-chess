package domain.board;

import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BoardTest {

    @Test
    @DisplayName("보드 좌측 바깥으로는 이동이 불가능하다")
    void moveLeftOutside() {
        Board board = new Board();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, -1);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보드 우측 바깥으로는 이동이 불가능하다")
    void moveRightOutside() {
        Board board = new Board();

        Coordinate start = new Coordinate(0, 7);
        Coordinate end = new Coordinate(0, 8);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보드 위쪽 바깥으로는 이동이 불가능하다")
    void moveDownOutside() {
        Board board = new Board();

        Coordinate start = new Coordinate(7, 0);
        Coordinate end = new Coordinate(8, 0);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보드 아래쪽 바깥으로는 이동이 불가능하다")
    void moveUpOutside() {
        Board board = new Board();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(-1, 0);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
