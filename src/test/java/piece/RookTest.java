package piece;

import java.util.Set;
import java.util.stream.Stream;
import model.Camp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import point.Position;

class RookTest {

    @DisplayName("이동할 수 없는 경로면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void cantMovable(final Position currentPosition, final Position nextPosition) {
        Rook rook = new Rook(Camp.BLACK);

        Assertions.assertThat(rook.canMovable(currentPosition, nextPosition)).isFalse();
    }

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Position currentPosition, final Position nextPosition) {
        Rook rook = new Rook(Camp.BLACK);

        Assertions.assertThatThrownBy(() -> rook.getRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("a8"), Position.from("b7")),
                Arguments.of(Position.from("a8"), Position.from("c4")),
                Arguments.of(Position.from("b3"), Position.from("f7")),
                Arguments.of(Position.from("e3"), Position.from("h5"))
        );
    }

    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Position currentPosition, final Position nextPosition) {
        Rook rook = new Rook(Camp.BLACK);
        Set<Position> route = rook.getRoute(currentPosition, nextPosition);
        System.out.println("======");
        for (Position position : route) {
            System.out.println(position);
        }
        System.out.println("======");

        Assertions.assertThat(rook.canMovable(currentPosition, nextPosition)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(Position.from("a8"), Position.from("a2")),
                Arguments.of(Position.from("h8"), Position.from("d8")),
                Arguments.of(Position.from("c3"), Position.from("c1")),
                Arguments.of(Position.from("g2"), Position.from("g6"))
        );
    }
}
