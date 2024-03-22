package model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(final Moving moving) {
        final Knight knight = new Knight(Camp.BLACK);

        assertAll(
                () -> assertThat(knight.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> knight.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 기물이 이동할 수 없는 위치입니다.")
        );
    }

    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("d5"), Position.from("a5"))),
                Arguments.of(new Moving(Position.from("a8"), Position.from("f3"))),
                Arguments.of(new Moving(Position.from("a8"), Position.from("e5")))
        );
    }

    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(final Moving moving) {
        final Knight knight = new Knight(Camp.BLACK);

        assertAll(
                () -> assertThat(knight.canMovable(moving)).isTrue(),
                () -> assertThat(knight.getMoveRoute(moving)).isEmpty()
        );
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("a8"), Position.from("c7"))),
                Arguments.of(new Moving(Position.from("a8"), Position.from("b6"))),
                Arguments.of(new Moving(Position.from("d6"), Position.from("c4"))),
                Arguments.of(new Moving(Position.from("d6"), Position.from("b5"))),
                Arguments.of(new Moving(Position.from("d6"), Position.from("b7"))),
                Arguments.of(new Moving(Position.from("d6"), Position.from("c8"))),
                Arguments.of(new Moving(Position.from("d6"), Position.from("e8"))),
                Arguments.of(new Moving(Position.from("d6"), Position.from("f7")))
        );
    }
}
