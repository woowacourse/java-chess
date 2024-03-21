package chess.piece;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹은 상하좌우 및 대각선 방향으로 한 칸 이동할 수 있다.")
    void kingMoveTest() {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        assertAll(
                () -> assertTrue(king.isMovable(source, Position.of("d", 5))),
                () -> assertTrue(king.isMovable(source, Position.of("e", 5))),
                () -> assertTrue(king.isMovable(source, Position.of("e", 4))),
                () -> assertTrue(king.isMovable(source, Position.of("e", 3))),
                () -> assertTrue(king.isMovable(source, Position.of("d", 3))),
                () -> assertTrue(king.isMovable(source, Position.of("c", 3))),
                () -> assertTrue(king.isMovable(source, Position.of("c", 4))),
                () -> assertTrue(king.isMovable(source, Position.of("c", 5)))
        );
    }

    @Test
    @DisplayName("킹은 한 번에 여러 칸 이동할 수 없다.")
    void kingMaxUnitTest() {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        assertFalse(king.isMovable(source, Position.of("d", 6)));
    }
}
