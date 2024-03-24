package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Route;
import chess.domain.board.SquareState;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    private static final Queen QUEEN = new Queen(Color.BLACK);

    @DisplayName("한 방향으로 이루어지지 않은 경로로 움직일 수 없다.")
    @Test
    void tooManyDirectionTest() {
        Route manyDirectionRoute = new Route(
                List.of(Direction.DOWN, Direction.UP),
                List.of(SquareState.EMPTY, SquareState.EMPTY)
        );

        Assertions.assertThat(QUEEN.canMove(manyDirectionRoute))
                .isFalse();
    }

    @DisplayName("경로 중간에 기물이 위치한다면 움직일 수 없다.")
    @Test
    void pathHasPieceTest() {
        Route notEmptyRoute = new Route(
                List.of(Direction.DOWN, Direction.DOWN, Direction.DOWN),
                List.of(SquareState.EMPTY, SquareState.ALLY, SquareState.EMPTY)
        );

        Assertions.assertThat(QUEEN.canMove(notEmptyRoute))
                .isFalse();
    }

    @DisplayName("목적지에 아군이 존재한다면 움직일 수 없다.")
    @Test
    void allyLocatedAtTargetTest() {
        Route manyDirectionRoute = new Route(
                List.of(Direction.DOWN, Direction.DOWN, Direction.DOWN),
                List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.ALLY));

        Assertions.assertThat(QUEEN.canMove(manyDirectionRoute))
                .isFalse();
    }

    @DisplayName("최대 7칸까지 움직일 수 있다.")
    @Test
    void maxDistanceMoveTest() {
        Route manyDirectionRoute = new Route(
                List.of(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN,
                        Direction.DOWN),
                List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY,
                        SquareState.EMPTY, SquareState.EMPTY)
        );

        Assertions.assertThat(QUEEN.canMove(manyDirectionRoute))
                .isTrue();
    }
}
