package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.math.UnitVector;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Position position;

    @BeforeEach
    void setUP() {
        position = Position.of(1, 1);
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_up() {
        var movedPosition = position.move(UnitVector.UP);

        assertThat(movedPosition.equals(Position.of(0, 1))).isTrue();
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_down() {
        var movedPosition = position.move(UnitVector.DOWN);

        assertThat(movedPosition.equals(Position.of(2, 1))).isTrue();
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_left() {
        var movedPosition = position.move(UnitVector.LEFT);

        assertThat(movedPosition.equals(Position.of(1, 0))).isTrue();
    }

    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_right() {
        var movedPosition = position.move(UnitVector.RIGHT);

        assertThat(movedPosition.equals(Position.of(1, 2))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_upRight() {
        var movedPosition = position.move(UnitVector.UP_RIGHT);

        assertThat(movedPosition.equals(Position.of(0, 2))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_upLeft() {
        var movedPosition = position.move(UnitVector.UP_LEFT);

        assertThat(movedPosition.equals(Position.of(0, 0))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_downLeft() {
        var position = Position.of(1, 1);

        var movedPosition = position.move(UnitVector.DOWN_RIGHT);

        assertThat(movedPosition.equals(Position.of(2, 2))).isTrue();
    }
    @Test
    @DisplayName("UnitVector를 받아 Position을 이동한다.")
    void moveTest_downRight() {
        var position = Position.of(1, 1);

        var movedPosition = position.move(UnitVector.DOWN_LEFT);

        assertThat(movedPosition.equals(Position.of(2, 0))).isTrue();
    }

    @Test
    @DisplayName("Position 객체는 논리적으로 같으면 같은 객체이다.")
    void equalsTest() {
        var a = Position.of(1, 1);
        var b = Position.of(1, 1);

        assertThat(a.equals(b)).isTrue();
    }

    @Test
    @DisplayName("사용자의 입력을 2차원 리스트의 좌표값으로 변환해준다.")
    void 사용자의_입력을_행열의_좌표값으로_변환해준다_case1() {
        String input = "g8";
        Position position = Position.toPosition(input);

        assertThat(position).isEqualTo(Position.of(0, 6));
    }

    @Test
    @DisplayName("사용자의 입력을 2차원 리스트의 좌표값으로 변환해준다.")
    void 사용자의_입력을_행열의_좌표값으로_변환해준다_case2() {
        String input = "h6";
        Position position = Position.toPosition(input);

        assertAll(
                () -> assertThat(position.getRow()).isEqualTo(2),
                () -> assertThat(position.getColumn()).isEqualTo(7)
        );
    }
}
