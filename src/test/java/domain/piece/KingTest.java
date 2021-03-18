package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {
    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("b6")),
                Arguments.of(Position.from("c6")),
                Arguments.of(Position.from("d6")),
                Arguments.of(Position.from("b5")),
                Arguments.of(Position.from("d5")),
                Arguments.of(Position.from("b4")),
                Arguments.of(Position.from("c4")),
                Arguments.of(Position.from("d4"))
        );
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        King king = new King(Color.BLACK, Position.from("c5"));
        king.move(position, new Pieces());
        assertTrue(king.hasPosition(position));
    }

    @Test
    void cornerMove() {
        King king = new King(Color.BLACK, Position.from("a5"));
        assertThatCode(() -> king.move(Position.from("a4"), new Pieces()))
                .doesNotThrowAnyException();
    }

}