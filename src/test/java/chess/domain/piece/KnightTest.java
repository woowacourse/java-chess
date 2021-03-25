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

    @Test
    @DisplayName("나이트의 움직임로직으로 갈 수 없는 위치를 입력받았을 때 익셉션을 잘 날리는지 확인")
    void knightWrongMove() {
        final Knight knight = new Knight(Color.BLACK, Position.from("b1"));
        assertThatThrownBy(() -> knight.moveToEmpty(Position.from("b2"), new Pieces()))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Knight.KNIGHT_MOVE_ERROR);
    }
}