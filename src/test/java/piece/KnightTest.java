package piece;

import java.util.stream.Stream;
import model.Camp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import point.Column;
import point.Position;
import point.Row;

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
                Arguments.of(new Position(Row.FIFTH, Column.FOURTH), new Position(Row.FIFTH, Column.FIRST)),
                Arguments.of(new Position(Row.EIGHTH, Column.FIRST), new Position(Row.THIRD, Column.SIXTH)),
                Arguments.of(new Position(Row.EIGHTH, Column.FIRST), new Position(Row.FIFTH, Column.FIFTH))
        );
    }

    @DisplayName("이동할 수 있는 경로면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Position currentPosition, final Position nextPosition) {
        Knight knight = new Knight(Camp.BLACK);

        Assertions.assertThat(knight.canMovable(currentPosition, nextPosition)).isTrue();
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Position(Row.EIGHTH, Column.FIRST), new Position(Row.SEVENTH, Column.THIRD)),
                Arguments.of(new Position(Row.EIGHTH, Column.FIRST), new Position(Row.SIXTH, Column.SECOND))
        );
    }
}
