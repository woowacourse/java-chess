package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    @DisplayName("킹의 움직임로직으로 갈 수 없는 위치를 입력받았을 때 익셉션을 잘 날리는지 확인")
    void kingWrongMove() {
        final King king = new King(Color.BLACK, Position.from("e1"));
        assertThatThrownBy(() -> king.moveToEmpty(Position.from("e3"), new Pieces()))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(King.KING_MOVE_ERROR);
    }
}