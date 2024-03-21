package chess.piece;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸은 상하좌우 및 대각선 방향으로 이동할 수 있다.")
    void queenMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        assertAll(
                () -> assertTrue(queen.isMovable(source, Position.of("d", 8))),
                () -> assertTrue(queen.isMovable(source, Position.of("d", 1))),
                () -> assertTrue(queen.isMovable(source, Position.of("a", 4))),
                () -> assertTrue(queen.isMovable(source, Position.of("h", 4))),
                () -> assertTrue(queen.isMovable(source, Position.of("f", 6))),
                () -> assertTrue(queen.isMovable(source, Position.of("b", 2))),
                () -> assertTrue(queen.isMovable(source, Position.of("f", 2))),
                () -> assertTrue(queen.isMovable(source, Position.of("b", 6)))
        );
    }
}
