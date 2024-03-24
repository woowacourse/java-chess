package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Route;
import chess.domain.board.SquareState;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    private static final Knight KNIGHT = new Knight(Color.BLACK);

    @DisplayName("직선과 대각선을 모두 포함하고 있지 않은 경로로 움직일 수 없다.")
    @Test
    void orthogonalDirectionTest() {
        Route orthogonalRoute = new Route(
                List.of(Direction.DOWN, Direction.UP),
                List.of(SquareState.EMPTY, SquareState.EMPTY)
        );

        Assertions.assertThat(KNIGHT.canMove(orthogonalRoute))
                .isFalse();
    }

    @DisplayName("경로 중간에 기물이 위치해도 움직일 수 있다.")
    @Test
    void pathHasPieceTest() {
        Route notEmptyRoute = new Route(
                List.of(Direction.DOWN, Direction.DOWN_LEFT),
                List.of(SquareState.ALLY, SquareState.EMPTY)
        );

        Assertions.assertThat(KNIGHT.canMove(notEmptyRoute))
                .isTrue();
    }

    @DisplayName("목적지에 아군이 존재한다면 움직일 수 없다.")
    @Test
    void allyLocatedAtTargetTest() {
        Route allyTargetRoute = new Route(
                List.of(Direction.DOWN, Direction.DOWN_LEFT),
                List.of(SquareState.EMPTY, SquareState.ALLY)
        );

        Assertions.assertThat(KNIGHT.canMove(allyTargetRoute))
                .isFalse();
    }

    @DisplayName("2칸이 아닌 경로로 움직일 수 없다.")
    @Test
    void maxDistanceMoveTest() {
        Route wrongDistanceRoute = new Route(
                List.of(Direction.DOWN, Direction.DOWN, Direction.DOWN_LEFT),
                List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY)
        );

        Assertions.assertThat(KNIGHT.canMove(wrongDistanceRoute))
                .isFalse();
    }
}
