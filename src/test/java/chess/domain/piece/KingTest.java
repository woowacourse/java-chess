package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Direction;
import chess.domain.board.LocationState;
import chess.domain.board.Path;
import chess.domain.board.Step;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KingTest {
    private static final King KING = new King(Color.BLACK);

    @DisplayName("한 칸의 빈 곳으로의 경로는 움직일 수 있다.")
    @ParameterizedTest
    @EnumSource(Direction.class)
    void canMoveTest(Direction direction) {
        Path path = new Path(List.of(
                new Step(direction, LocationState.EMPTY)
        ));

        assertThat(KING.canMove(path)).isTrue();
    }

    @DisplayName("경로의 끝에 아군이 있다면 움직일 수 없다.")
    @ParameterizedTest
    @EnumSource(Direction.class)
    void allyLocatedAtTargetTest(Direction direction) {
        Path path = new Path(List.of(
                new Step(direction, LocationState.ALLY)
        ));

        assertThat(KING.canMove(path)).isFalse();
    }

    @DisplayName("경로의 끝에 적군이 있다면 움직일 수 있다.")
    @ParameterizedTest
    @EnumSource(Direction.class)
    void enemyLocatedAtTargetTest(Direction direction) {
        Path path = new Path(List.of(
                new Step(direction, LocationState.ENEMY)
        ));

        assertThat(KING.canMove(path)).isTrue();
    }

    @DisplayName("두 칸 이상의 경로는 움직일 수 없다.")
    @Test
    void tooLongPathTest() {
        Path path = new Path(List.of(
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.DOWN, LocationState.EMPTY)
        ));

        assertThat(KING.canMove(path)).isFalse();
    }
}
