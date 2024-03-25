package model.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovingTest {

    @DisplayName("현재 위치에서 다음 위치까지 수평으로 이동하는 경우 true를 반환한다.")
    @Test
    void isHorizontal() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("f2"));

        assertThat(moving.isHorizontal()).isTrue();
    }

    @DisplayName("현재 위치에서 다음 위치까지 수평으로 이동하지 않는 경우 false를 반환한다.")
    @Test
    void isNotHorizontal() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("f3"));

        assertThat(moving.isHorizontal()).isFalse();
    }

    @DisplayName("현재 위치에서 다음 위치까지 수직으로 이동하는 경우 true를 반환한다.")
    @Test
    void isVertical() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("a5"));

        assertThat(moving.isVertical()).isTrue();
    }

    @DisplayName("현재 위치에서 다음 위치까지 수직으로 이동하지 않는 경우 false를 반환한다.")
    @Test
    void isNotVertical() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("b5"));

        assertThat(moving.isVertical()).isFalse();
    }

    @DisplayName("현재 위치에서 다음 위치까지 대각선으로 이동하는 경우 true를 반환한다.")
    @Test
    void isDiagonal() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("d5"));

        assertThat(moving.isDiagonal()).isTrue();
    }

    @DisplayName("현재 위치에서 다음 위치까지 대각선으로 이동하지 않는 경우 false를 반환한다.")
    @Test
    void isNotDiagonal() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("c5"));

        assertThat(moving.isDiagonal()).isFalse();
    }

    @DisplayName("현재 위치와 다음 위치가 동일한 경우 true를 반환한다.")
    @Test
    void isNotMoved() {
        final Moving moving = new Moving(Position.from("d5"), Position.from("d5"));

        assertThat(moving.isNotMoved()).isTrue();
    }

    @DisplayName("현재 위치와 다음 위치가 동일하지 않는 경우 true를 반환한다.")
    @Test
    void isMoved() {
        final Moving moving = new Moving(Position.from("d5"), Position.from("d6"));

        assertThat(moving.isNotMoved()).isFalse();
    }

    @DisplayName("현재 위치에서 다음 위치까지 수평으로 이동할 경우 이동 경로를 반환한다.")
    @Test
    void horizontalRoute() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("f2"));

        final Set<Position> expected = Set.of(Position.from("b2"), Position.from("c2"), Position.from("d2"),
                Position.from("e2"));

        assertThat(moving.route()).isEqualTo(expected);
    }

    @DisplayName("현재 위치에서 다음 위치까지 수직으로 이동할 경우 이동 경로를 반환한다.")
    @Test
    void verticalRoute() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("a5"));

        final Set<Position> expected = Set.of(Position.from("a3"), Position.from("a4"));

        assertThat(moving.route()).isEqualTo(expected);
    }

    @DisplayName("현재 위치에서 다음 위치까지 대각선으로 이동할 경우 이동 경로를 반환한다.")
    @Test
    void diagonalRoute() {
        final Moving moving = new Moving(Position.from("a2"), Position.from("d5"));

        final Set<Position> expected = Set.of(Position.from("b3"), Position.from("c4"));

        assertThat(moving.route()).isEqualTo(expected);
    }
}
