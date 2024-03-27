package domain.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovableTest {
    @DisplayName("잘못된 최대 이동 횟수가 입력된 경우 예외처리한다.")
    @Test
    void testInvalidMaxMovement() {
        Assertions.assertThatThrownBy(() -> new Movable(-1, Direction.LEFT_DOWN))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("대각선 위 방향으로 최대 2번 이동 가능할 때 대각선 위 방향으로 1번 이동하려 하면 성공한다.")
    @Test
    void successToMove() {
        Movable movable = new Movable(2, Direction.NORTH_EAST);
        boolean actual = movable.canMove(
                new Position(new File('a'), new Rank(1)),
                new Position(new File('b'), new Rank(2))
        );
        assertTrue(actual);
    }

    @DisplayName("대각선 위 방향으로 최대 2번 이동 가능할 때 북쪽 방향으로 이동하려 하면 실패한다.")
    @Test
    void failToMoveIncorrectDirection() {
        Movable movable = new Movable(2, Direction.NORTH_EAST);
        boolean actual = movable.canMove(
                new Position(new File('a'), new Rank(1)),
                new Position(new File('a'), new Rank(2))
        );
        assertFalse(actual);
    }

    @DisplayName("대각선 위 방향으로 최대 2번 이동 가능할 때 대각선 방향으로 세 번 이동하려 하면 실패한다.")
    @Test
    void failToMoveOverMaxMovement() {
        Movable movable = new Movable(2, Direction.NORTH_EAST);
        boolean actual = movable.canMove(
                new Position(new File('a'), new Rank(1)),
                new Position(new File('d'), new Rank(4))
        );
        assertFalse(actual);
    }
}
