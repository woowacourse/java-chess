package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Path;
import chess.domain.board.SquareState;
import chess.domain.board.Step;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    private static final Rook ROOK = new Rook(Color.BLACK);

    @DisplayName("한 방향으로 이루어지지 않은 경로로 움직일 수 없다.")
    @Test
    void tooManyDirectionTest() {
        Path manyDirectionPath = new Path(List.of(
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.UP, SquareState.EMPTY)
        ));

        Assertions.assertThat(ROOK.canMove(manyDirectionPath))
                .isFalse();
    }

    @DisplayName("경로 중간에 기물이 위치한다면 움직일 수 없다.")
    @Test
    void pathHasPieceTest() {
        Path notEmptyPath = new Path(List.of(
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.ALLY),
                new Step(Direction.DOWN, SquareState.EMPTY)
        ));

        Assertions.assertThat(ROOK.canMove(notEmptyPath))
                .isFalse();
    }

    @DisplayName("목적지에 아군이 존재한다면 움직일 수 없다.")
    @Test
    void allyLocatedAtTargetTest() {
        Path manyDirectionPath = new Path(List.of(
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.ALLY)
        ));

        Assertions.assertThat(ROOK.canMove(manyDirectionPath))
                .isFalse();
    }

    @DisplayName("대각선 방향으로 움직일 수 없다.")
    @Test
    void canNotMoveDiagonalTest() {
        Path diagonalDirectionPath = new Path(List.of(
                new Step(Direction.UP_RIGHT, SquareState.EMPTY),
                new Step(Direction.UP_RIGHT, SquareState.EMPTY),
                new Step(Direction.UP_RIGHT, SquareState.EMPTY)
        ));

        Assertions.assertThat(ROOK.canMove(diagonalDirectionPath))
                .isFalse();
    }

    @DisplayName("최대 7칸까지 움직일 수 있다.")
    @Test
    void maxDistanceMoveTest() {
        Path manyDirectionPath = new Path(List.of(
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.ENEMY)
        ));

        Assertions.assertThat(ROOK.canMove(manyDirectionPath))
                .isTrue();
    }
}
