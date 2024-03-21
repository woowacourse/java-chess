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

class KingTest {

    @DisplayName("이동할 수 없는 경로면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("cantMovableParameterProvider")
    void invalidRoute(Moving moving) {
        King king = new King(Camp.BLACK);

        assertAll(
                () -> assertThat(king.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> king.getMoveRoute(moving))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }


    static Stream<Arguments> cantMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("d6"), Position.from("b5"))),
                Arguments.of(new Moving(Position.from("f5"), Position.from("a8"))),
                Arguments.of(new Moving(Position.from("d3"), Position.from("h2"))),
                Arguments.of(new Moving(Position.from("h6"), Position.from("g4")))
        );
    }


    @DisplayName("이동할 수 있다면 경로를 반환한다.")
    @ParameterizedTest
    @MethodSource("canMovableParameterProvider")
    void canMovable(Moving moving) {
        King king = new King(Camp.BLACK);

        assertAll(
                () -> assertThat(king.canMovable(moving)).isTrue(),
                () -> assertThat(king.getMoveRoute(moving)).isEmpty()
        );
    }

    static Stream<Arguments> canMovableParameterProvider() {
        return Stream.of(
                Arguments.of(new Moving(Position.from("e8"), Position.from("d8"))),
                Arguments.of(new Moving(Position.from("e8"), Position.from("e7"))),
                Arguments.of(new Moving(Position.from("e8"), Position.from("d7"))),
                Arguments.of(new Moving(Position.from("e8"), Position.from("f7"))),
                Arguments.of(new Moving(Position.from("e8"), Position.from("f8"))),
                Arguments.of(new Moving(Position.from("e1"), Position.from("d2"))),
                Arguments.of(new Moving(Position.from("e1"), Position.from("e2"))),
                Arguments.of(new Moving(Position.from("e1"), Position.from("f2")))
        );
    }
}
