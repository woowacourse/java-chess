package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PositionTest {

    @DisplayName("범위 내 포지션을 입력하면 정상적으로 객체가 생성된다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @ValueSource(strings = {"a1", "a8", "h1", "h8"})
    void testCreatePosition(String input) {
        assertThatCode(() -> Position.of(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("범위 밖의 포지션을 입력하면 에러가 발생한다.")
    @Test
    void testFail() {
        assertThatThrownBy(() -> Position.of("a0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 값의 Column과 Row를 가진 Position은 같은 Position이다")
    @Test
    void testSamePosition() {
        Position position1 = Position.of("a1");
        Position position2 = Position.of("a1");

        assertThat(position1).isEqualTo(position2);
    }

    @DisplayName("출발 위치와 도착 위치가 직선상에 존재하면 true를 반환한다 (북)")
    @Test
    void testCanCrossMovingStraightNorth() {
        Position src = Position.of("d3");
        Position dest = Position.of("d5");

        assertThat(src.canCrossMovingStraight(Direction.NORTH, dest)).isTrue();
    }

    @DisplayName("출발 위치와 도착 위치가 직선상에 존재하면 true를 반환한다 (북동)")
    @Test
    void testCanCrossMovingStraightCross() {
        Position src = Position.of("d5");
        Position dest = Position.of("g8");

        assertThat(src.canCrossMovingStraight(Direction.NORTH_EAST, dest)).isTrue();
    }

    @DisplayName("출발 위치와 도착 위치가 직선상에 존재하지 않으면 false를 반환한다")
    @Test
    void testCanCrossMovingStraight() {
        Position src = Position.of("d3");
        Position dest = Position.of("d1");

        assertThat(src.canCrossMovingStraight(Direction.NORTH, dest)).isFalse();
    }
}
