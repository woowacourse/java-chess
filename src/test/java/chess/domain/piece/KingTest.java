package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("킹의 움직임 로직확인")
    void move(final Position position) {
        final King king = new King(Color.BLACK, Position.from("c5"));
        king.moveToEmpty(position, new Pieces());
        assertTrue(king.hasPosition(position));
    }

    @Test
    @DisplayName("킹이 코너에 있을 때 움직임 로직확인")
    void cornerMove() {
        final King king = new King(Color.BLACK, Position.from("a5"));
        assertThatCode(() -> king.moveToEmpty(Position.from("a4"), new Pieces()))
                .doesNotThrowAnyException();
    }
}