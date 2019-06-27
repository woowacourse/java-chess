package chess.domain.domain.direction;

import chess.domain.Aliance;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.direction.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectionTest {
    Board board;
    Direction direction;

    @BeforeEach
    void setUp() {
        board = new Board(Aliance.WHITE);
    }

    @Test
    void 위쪽방향_아래방향() {
        direction = VerticalDirection.getInstance();
        assertThat(direction.simulateUnitMove(board, Position.valueOf("a2"), false)).isEqualTo(Position.valueOf("a3"));
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("b1"));
    }

    @Test
    void 위쪽방향_아래방향_장애물_있는_경우_예외_반환() {
        direction = VerticalDirection.getInstance();
        assertThrows(IllegalArgumentException.class, () -> direction.simulateUnitMove(board, Position.valueOf("b2"), true));
    }

    @Test
    void 오른쪽방향_왼쪽방향() {
        direction = HorizonDirection.getInstance();
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("b2"),false)).isEqualTo(Position.valueOf("c2"));
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("a2"));
    }

    @Test
    void 오른쪽방향_왼쪽방향_장애물_있는_경우_예외_반환() {
        direction = HorizonDirection.getInstance();
        assertThrows(IllegalArgumentException.class, () -> direction.simulateUnitMove(board, Position.valueOf("b2"), true));
    }

    @Test
    void 오른대각선방향() {
        direction = RightDiagonalDirection.getInstance();
        assertThat(direction.simulateUnitMove(board, Position.valueOf("b2"), false)).isEqualTo(Position.valueOf("c3"));
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("a1"));
    }

    @Test
    void 오른대각선방향_장애물_있는_경우_예외_반환() {
        direction = RightDiagonalDirection.getInstance();
        assertThrows(IllegalArgumentException.class, () -> direction.simulateUnitMove(board, Position.valueOf("b2"), true));
    }

    @Test
    void 왼대각선방향() {
        direction = LeftDiagonalDirection.getInstance();
        assertThat(direction.simulateUnitMove(board, Position.valueOf("b2"), false)).isEqualTo(Position.valueOf("a3"));
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("c1"));
    }

    @Test
    void 왼대각선방향_장애물_있는_경우_예외_반환() {
        direction = LeftDiagonalDirection.getInstance();
        assertThrows(IllegalArgumentException.class, () -> direction.simulateUnitMove(board, Position.valueOf("b2"), true));
    }

    @Test
    void 위아래오른방향_나이트() {
        direction = VerticalRightKnightDirection.getInstance();
        assertThat(direction.simulateUnitMove(board, Position.valueOf("d2"), false)).isEqualTo(Position.valueOf("e4"));
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("d4"),true)).isEqualTo(Position.valueOf("c2"));
    }

    @Test
    void 위아래왼방향_나이트() {
        direction = VerticalLeftKnightDirection.getInstance();
        assertThat(direction.simulateUnitMove(board, Position.valueOf("d2"), false)).isEqualTo(Position.valueOf("c4"));
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("d4"),true)).isEqualTo(Position.valueOf("e2"));
    }

    @Test
    void 양옆오른방향_나이트() {
        direction = HorizonRightKnightDirection.getInstance();
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("d4"),false)).isEqualTo(Position.valueOf("f3"));
        assertThat(direction.simulateUnitMove(board, Position.valueOf("d2"), true)).isEqualTo(Position.valueOf("b3"));
    }

    @Test
    void 양옆왼방향_나이트() {
        direction = HorizonLeftKnightDirection.getInstance();
        assertThat(direction.simulateUnitMove(board, Position.valueOf("d2"), false)).isEqualTo(Position.valueOf("f3"));
        //assertThat(direction.simulateUnitMove(board, Position.valueOf("d4"),true)).isEqualTo(Position.valueOf("b3"));
    }

    @Test
    void 나이트방향_중간에_장애물_있는_경우_확인() {
        direction = VerticalRightKnightDirection.getInstance();
        assertDoesNotThrow(() -> direction.simulateUnitMove(board, Position.valueOf("b1"), false));
    }
}
