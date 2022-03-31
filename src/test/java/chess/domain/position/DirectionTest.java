package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    @DisplayName("수직 방향일 경우")
    void isVerticalTrue() {
        assertThat(Direction.isVertical(Position.from("a1"), Position.from("a8"))).isTrue();
    }

    @Test
    @DisplayName("수직 방향이 아닐 경우")
    void isVerticalFalse() {
        assertThat(Direction.isVertical(Position.from("a1"), Position.from("h1"))).isFalse();
    }

    @Test
    @DisplayName("수평 방향일 경우")
    void isHorizontalTrue() {
        assertThat(Direction.isHorizontal(Position.from("a1"), Position.from("h1"))).isTrue();
    }

    @Test
    @DisplayName("수평 방향이 아닐 경우")
    void isHorizontalFalse() {
        assertThat(Direction.isHorizontal(Position.from("a1"), Position.from("a8"))).isFalse();
    }

    @Test
    @DisplayName("대각선 방향일 경우")
    void isDiagonalTrue() {
        assertThat(Direction.isDiagonal(Position.from("a1"), Position.from("h8"))).isTrue();
    }

    @Test
    @DisplayName("대각선 방향이 아닐 경우")
    void isDiagonalFalse() {
        assertThat(Direction.isDiagonal(Position.from("a1"), Position.from("a8"))).isFalse();
    }

    @Test
    @DisplayName("방향 정보 조회")
    void getDirection() {
        Position from = Position.from("a1");
        Position to = Position.from("a2");

        assertThat(Direction.getDirection(from, to)).isEqualTo(Direction.UP);
    }

    @Test
    @DisplayName("존재하지 않는 방향을 조회할 경우")
    void getDirectionInvalid() {
        Position from = Position.from("a1");
        Position to = Position.from("a8");

        assertThat(Direction.getDirection(from, to)).isEqualTo(Direction.INVALID);
    }
}
