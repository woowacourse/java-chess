package piece;

import java.util.stream.Stream;
import model.Camp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import point.Position;

class BishopTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(final Position currentPosition, final Position nextPosition) {
        Bishop bishop = new Bishop(Camp.BLACK);

        Assertions.assertThat(bishop.canMovable(currentPosition, nextPosition)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Position currentPosition, final Position nextPosition) {
        Bishop bishop = new Bishop(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> bishop.getRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }


    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("c8"), Position.from("a8")),
                Arguments.of(Position.from("c8"),Position.from("c3")),
                Arguments.of(Position.from("f8"), Position.from("e8")),
                Arguments.of(Position.from("g4"), Position.from("c3"))
        );
    }


    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Position currentPosition, final Position nextPosition, final int size) {
        Bishop bishop = new Bishop(Camp.BLACK);

        Assertions.assertThat(bishop.canMovable(currentPosition, nextPosition)).isTrue();
        Assertions.assertThat(bishop.getRoute(currentPosition, nextPosition)).hasSize(size);
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("c8"), Position.from("e6"), 1),
                Arguments.of(Position.from("f8"), Position.from("a3"), 4),
                Arguments.of(Position.from("c4"), Position.from("a6"), 1),
                Arguments.of(Position.from("c4"), Position.from("f7"), 2)
        );
    }
}
