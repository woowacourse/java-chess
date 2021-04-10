package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovesTest {
    @Test
    @DisplayName("위")
    void moveUp() {
        Position before = Position.valueOf("c3");

        Position after = Moves.UP.move(before);

        assertThat(after).isEqualTo(Position.valueOf("c4"));
    }

    @Test
    @DisplayName("아래")
    void moveDown() {
        Position before = Position.valueOf("c3");

        Position after = Moves.DOWN.move(before);

        assertThat(after).isEqualTo(Position.valueOf("c2"));
    }

    @Test
    @DisplayName("오른쪽")
    void moveRight() {
        Position before = Position.valueOf("c3");

        Position after = Moves.RIGHT.move(before);

        assertThat(after).isEqualTo(Position.valueOf("d3"));
    }

    @Test
    @DisplayName("왼쪽")
    void moveLeft() {
        Position before = Position.valueOf("c3");

        Position after = Moves.LEFT.move(before);

        assertThat(after).isEqualTo(Position.valueOf("b3"));
    }

    @Test
    @DisplayName("오른쪽 대각선 위")
    void moveRightUp() {
        Position before = Position.valueOf("c3");

        Position after = Moves.RIGHT_UP.move(before);

        assertThat(after).isEqualTo(Position.valueOf("d4"));
    }

    @Test
    @DisplayName("오른쪽 대각선 아래")
    void moveRightDown() {
        Position before = Position.valueOf("c3");

        Position after = Moves.RIGHT_DOWN.move(before);

        assertThat(after).isEqualTo(Position.valueOf("d2"));
    }

    @Test
    @DisplayName("왼쪽 대각선 위")
    void moveLeftUp() {
        Position before = Position.valueOf("c3");

        Position after = Moves.LEFT_UP.move(before);

        assertThat(after).isEqualTo(Position.valueOf("b4"));
    }

    @Test
    @DisplayName("왼쪽 대각선 아래")
    void moveLeftDown() {
        Position before = Position.valueOf("c3");

        Position after = Moves.LEFT_DOWN.move(before);

        assertThat(after).isEqualTo(Position.valueOf("b2"));
    }

    @Test
    @DisplayName("이동시 범위를 벗어나는 경우")
    void moveDown_fail() {
        Position before = Position.valueOf("a1");

        Position afterDown = Moves.DOWN.move(before);

        Position afterLeft = Moves.LEFT.move(before);

        assertThat(afterDown).isEqualTo(null);
        assertThat(afterLeft).isEqualTo(null);
    }

}
