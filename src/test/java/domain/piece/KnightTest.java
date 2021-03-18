package domain.piece;

import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("b7")),
                Arguments.of(Position.from("d7")),
                Arguments.of(Position.from("e6")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("d3")),
                Arguments.of(Position.from("b3")),
                Arguments.of(Position.from("a4")),
                Arguments.of(Position.from("a6"))
        );
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        Knight knight = new Knight(Color.BLACK, Position.from("c5"));
        knight.move(position, Lists.emptyList());
        assertTrue(knight.hasPosition(position));
    }

    @Test
    void cornerMove() {
        Knight knight = new Knight(Color.BLACK, Position.from("a5"));
        assertThatCode(() -> knight.move(Position.from("c4"), Lists.emptyList()))
                .doesNotThrowAnyException();
    }
}