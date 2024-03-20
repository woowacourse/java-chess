package piece;

import java.util.stream.Stream;
import model.Camp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import point.Position;

class QueenTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(final Position currentPosition, final Position nextPosition) {
        Queen queen = new Queen(Camp.BLACK);

        Assertions.assertThat(queen.canMovable(currentPosition, nextPosition)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Position currentPosition, final Position nextPosition) {
        Queen queen = new Queen(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> queen.getRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }


    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("b3"), Position.from("b3")),
                Arguments.of(Position.from("d8"), Position.from("e5")),
                Arguments.of(Position.from("d6"), Position.from("a2")),
                Arguments.of(Position.from("b5"), Position.from("a8")),
                Arguments.of(Position.from("h1"), Position.from("c5"))
        );
    }


    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Position currentPosition, final Position nextPosition) {
        Queen queen = new Queen(Camp.BLACK);

        Assertions.assertThat(queen.canMovable(currentPosition, nextPosition)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("d8"), Position.from("d1")),
                Arguments.of(Position.from("d8"), Position.from("a8")),
                Arguments.of(Position.from("d8"), Position.from("h8")),
                Arguments.of(Position.from("d8"), Position.from("b6")),
                Arguments.of(Position.from("d8"), Position.from("f6")),
                Arguments.of(Position.from("e4"), Position.from("e8")),
                Arguments.of(Position.from("e4"), Position.from("c6")),
                Arguments.of(Position.from("e4"), Position.from("h7"))
        );
    }
}
