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
    @DisplayName("나이트의 움직임 로직확인")
    void move(final Position position) {
        final Knight knight = new Knight(Color.BLACK, Position.from("c5"));
        knight.moveToEmpty(position, new Pieces());
        assertTrue(knight.hasPosition(position));
    }

    @Test
    @DisplayName("나이트가 코너에 있을 때 움직임 로직확인")
    void cornerMove() {
        final Knight knight = new Knight(Color.BLACK, Position.from("a5"));
        assertThatCode(() -> knight.moveToEmpty(Position.from("c4"), new Pieces()))
                .doesNotThrowAnyException();
    }
}