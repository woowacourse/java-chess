package piece;

import java.util.stream.Stream;
import model.Camp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import point.Position;

class KnightTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(final Position currentPosition, final Position nextPosition) {
        Knight knight = new Knight(Camp.BLACK);

        Assertions.assertThat(knight.canMovable(currentPosition, nextPosition)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Position currentPosition, final Position nextPosition) {
        Knight knight = new Knight(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> knight.getRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("d5"), Position.from("a5")),
                Arguments.of(Position.from("a8"), Position.from("f3")),
                Arguments.of(Position.from("a8"), Position.from("e5"))
        );
    }

    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Position currentPosition, final Position nextPosition) {
        Knight knight = new Knight(Camp.BLACK);

        Assertions.assertThat(knight.canMovable(currentPosition, nextPosition)).isTrue();
    }

    //TODO : 테스트 케이스 추가
    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("a8"), Position.from("c7")),
                Arguments.of(Position.from("a8"), Position.from("b6"))
        );
    }
}
