package chess.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.board.Direction;
import chess.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KnightTest {

    @Test
    @DisplayName("나이트는 두 칸 전진한 뒤, 전진한 방향의 90도 좌/우 한 칸으로 이동할 수 있다")
    void knightMoveTest() {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        Assertions.assertAll(
                () -> assertTrue(knight.isMovable(source, Position.of("f", 5))),
                () -> assertTrue(knight.isMovable(source, Position.of("b", 5))),
                () -> assertTrue(knight.isMovable(source, Position.of("f", 3))),
                () -> assertTrue(knight.isMovable(source, Position.of("b", 3))),
                () -> assertTrue(knight.isMovable(source, Position.of("e", 6))),
                () -> assertTrue(knight.isMovable(source, Position.of("c", 6))),
                () -> assertTrue(knight.isMovable(source, Position.of("e", 2))),
                () -> assertTrue(knight.isMovable(source, Position.of("c", 2)))
        );
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class)
    @DisplayName("나이트가 이동할 수 없는 경우를 판단한다.")
    void knightInvalidMoveTest(Direction direction) {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of("d", 4);
        Position destination = direction.nextPosition(source);
        // when, then
        assertFalse(knight.isMovable(source, destination));
    }
}
