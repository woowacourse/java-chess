package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Position position;

    @BeforeEach
    void setUP() {
        position = new Position(1, 1);
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_up() {
        Position movedPosition = position.move(UnitVector.UP);

        assertThat(movedPosition.equals(new Position(0, 1))).isTrue();
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_down() {
        Position movedPosition = position.move(UnitVector.DOWN);

        assertThat(movedPosition.equals(new Position(2, 1))).isTrue();
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_left() {
        Position movedPosition = position.move(UnitVector.LEFT);

        assertThat(movedPosition.equals(new Position(1, 0))).isTrue();
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_right() {
        Position movedPosition = position.move(UnitVector.RIGHT);

        assertThat(movedPosition.equals(new Position(1, 2))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_upRight() {
        Position movedPosition = position.move(UnitVector.UP_RIGHT);

        assertThat(movedPosition.equals(new Position(0, 2))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_upLeft() {
        Position movedPosition = position.move(UnitVector.UP_LEFT);

        assertThat(movedPosition.equals(new Position(0, 0))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_downLeft() {
        Position position = new Position(1, 1);

        Position movedPosition = position.move(UnitVector.DOWN_RIGHT);

        assertThat(movedPosition.equals(new Position(2, 2))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_downRight() {
        Position position = new Position(1, 1);

        Position movedPosition = position.move(UnitVector.DOWN_LEFT);

        assertThat(movedPosition.equals(new Position(2, 0))).isTrue();
    }

    @Test
    @DisplayName("Position 객체는 논리적으로 같으면 같은 객체이다.")
    void equalsTest() {
        Position a = new Position(1, 1);
        Position b = new Position(1, 1);

        assertThat(a.equals(b)).isTrue();
    }
}
