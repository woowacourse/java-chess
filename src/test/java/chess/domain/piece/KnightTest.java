package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Path;
import chess.domain.board.LocationState;
import chess.domain.board.Step;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    private static final Knight KNIGHT = new Knight(Color.BLACK);

    @DisplayName("직선과 대각선을 모두 포함하고 있지 않은 경로로 움직일 수 없다.")
    @Test
    void orthogonalDirectionTest() {
        Path orthogonalPath = new Path(List.of(
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.UP, LocationState.EMPTY)
        ));

        Assertions.assertThat(KNIGHT.canMove(orthogonalPath))
                .isFalse();
    }

    @DisplayName("경로 중간에 기물이 위치해도 움직일 수 있다..")
    @Test
    void pathHasPieceTest() {
        Path notEmptyPath = new Path(List.of(
                new Step(Direction.DOWN, LocationState.ALLY),
                new Step(Direction.DOWN_LEFT, LocationState.EMPTY)
        ));

        Assertions.assertThat(KNIGHT.canMove(notEmptyPath))
                .isTrue();
    }

    @DisplayName("목적지에 아군이 존재한다면 움직일 수 없다.")
    @Test
    void allyLocatedAtTargetTest() {
        Path allyTargetPath = new Path(List.of(
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.DOWN_LEFT, LocationState.ALLY)
        ));

        Assertions.assertThat(KNIGHT.canMove(allyTargetPath))
                .isFalse();
    }

    @DisplayName("2칸이 아닌 경로로 움직일 수 없다.")
    @Test
    void maxDistanceMoveTest() {
        Path wrongDistancePath = new Path(List.of(
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.DOWN_LEFT, LocationState.EMPTY)
        ));

        Assertions.assertThat(KNIGHT.canMove(wrongDistancePath))
                .isFalse();
    }
}
