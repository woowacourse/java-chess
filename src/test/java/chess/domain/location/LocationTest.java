package chess.domain.location;

import chess.domain.board.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class LocationTest {
    @DisplayName("입력받은 방향으로 위치를 이동한다.")
    @Test
    void moveTest() {
        Location location = Location.of("A1");
        location = location.move(Direction.UP_RIGHT);
        Assertions.assertThat(location.equals(Location.of("B2"))).isTrue();
    }

    @DisplayName("타겟과 해당 위치 간의 수직 거리를 구한다.")
    @Test
    void calculateVerticalDistanceTest() {
        Location location = Location.of("A1");
        int verticalDistance = location.calculateVerticalDistance(Location.of("B3"));
        Assertions.assertThat(verticalDistance).isEqualTo(2);
    }

    @DisplayName("타겟과 해당 위치 간의 수평 거리를 구한다.")
    @Test
    void calculateHorizontalDistanceTest() {
        Location location = Location.of("A1");
        int horizontalDistance = location.calculateHorizontalDistance(Location.of("B3"));
        Assertions.assertThat(horizontalDistance).isEqualTo(1);
    }

    @DisplayName("입력받은 문자열로 객체를 생성할 수 있다.")
    @Nested
    class ConstructTest {
        @DisplayName("소문자 + 숫자로 객체를 생성할 수 있다.")
        @Test
        void lowerCaseConstructTest() {
            Location location = Location.of("a1");
            Assertions.assertThat(location).isEqualTo(new Location(Column.A, Row.ONE));
        }

        @DisplayName("대문자 + 숫자로 객체를 생성할 수 있다.")
        @Test
        void upperCaseConstructTest() {
            Location location = Location.of("A1");
            Assertions.assertThat(location).isEqualTo(new Location(Column.A, Row.ONE));
        }

        @DisplayName("범위를 벗어난 입력으로 객체를 생성하면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"I1", "A9"})
        void outOfBoundConstructTest(String outOfBoundInput) {
            Assertions.assertThatThrownBy(() -> Location.of(outOfBoundInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 위치 입력입니다.");
        }

        @DisplayName("빈 문자열로 객체를 생성하면 예외가 발생한다.")
        @ParameterizedTest
        @NullAndEmptySource
        void emptyInputConstructTest(String emptyInput) {
            Assertions.assertThatThrownBy(() -> Location.of(emptyInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 위치 입력입니다.");
        }

        @DisplayName("형식에 맞지 않는 문자열로 객체를 생성하면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"A", "AA", "1A", "12", "A1B"})
        void wrongPatternInputConstructTest(String wrongInput) {
            Assertions.assertThatThrownBy(() -> Location.of(wrongInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 위치 입력입니다.");
        }
    }

}
