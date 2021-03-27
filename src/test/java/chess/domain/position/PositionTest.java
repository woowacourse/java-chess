package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertThatCode(() -> Position.valueOf("a1")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("잘못된 포지션 위치 - 벗어난 위치")
    void failChessBoardPosition() {
        assertThatThrownBy(() -> Position.valueOf("b9"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("잘못된 포지션 위치 - 길이초과")
    void failChessBoardPosition1() {
        assertThatThrownBy(() -> Position.valueOf("b10"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("올바른 포지션 위치")
    void successChessBoardPosition() {
        assertThatCode(() -> Position.valueOf("b1")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("위")
    void moveUp() {
        Position before = Position.valueOf("c3");

        Position after = before.moveUp();

        assertThat(after).isEqualTo(Position.valueOf("c4"));
    }

    @Test
    @DisplayName("아래")
    void moveDown() {
        Position before = Position.valueOf("c3");

        Position after = before.moveDown();

        assertThat(after).isEqualTo(Position.valueOf("c2"));
    }

    @Test
    @DisplayName("오른쪽")
    void moveRight() {
        Position before = Position.valueOf("c3");

        Position after = before.moveRight();

        assertThat(after).isEqualTo(Position.valueOf("d3"));
    }

    @Test
    @DisplayName("왼쪽")
    void moveLeft() {
        Position before = Position.valueOf("c3");

        Position after = before.moveLeft();

        assertThat(after).isEqualTo(Position.valueOf("b3"));
    }

    @Test
    @DisplayName("오른쪽 대각선 위")
    void moveRightUp() {
        Position before = Position.valueOf("c3");

        Position after = before.moveRightUp();

        assertThat(after).isEqualTo(Position.valueOf("d4"));
    }

    @Test
    @DisplayName("오른쪽 대각선 아래")
    void moveRightDown() {
        Position before = Position.valueOf("c3");

        Position after = before.moveRightDown();

        assertThat(after).isEqualTo(Position.valueOf("d2"));
    }

    @Test
    @DisplayName("왼쪽 대각선 위")
    void moveLeftUp() {
        Position before = Position.valueOf("c3");

        Position after = before.moveLeftUp();

        assertThat(after).isEqualTo(Position.valueOf("b4"));
    }

    @Test
    @DisplayName("왼쪽 대각선 아래")
    void moveLeftDown() {
        Position before = Position.valueOf("c3");

        Position after = before.moveLeftDown();

        assertThat(after).isEqualTo(Position.valueOf("b2"));
    }

    @Test
    @DisplayName("이동시 범위를 벗어나는 경우")
    void moveDown_fail() {
        Position before = Position.valueOf("a1");

        Position afterDown = before.moveDown();

        Position afterLeft = before.moveLeft();

        assertThat(afterDown).isEqualTo(Position.ERROR);
        assertThat(afterLeft).isEqualTo(Position.ERROR);
    }

}