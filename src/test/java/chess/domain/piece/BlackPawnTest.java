package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Direction;
import chess.domain.board.LocationState;
import chess.domain.board.Path;
import chess.domain.board.Step;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class BlackPawnTest {
    private BlackPawn BLACK_PAWN;

    @BeforeEach
    void beforeEach() {
        BLACK_PAWN = new BlackPawn();
    }

    @DisplayName("반대 방향으로 이동할 수 없다.")
    @Nested
    class ForwardTest {
        @DisplayName("블랙 폰은 위로 이동할 수 없다")
        @Test
        void blackPawnUpDirectionTest() {
            Path path = new Path(List.of(
                    new Step(Direction.UP, LocationState.EMPTY)
            ));

            assertThat(BLACK_PAWN.canMove(path)).isFalse();
        }

        @DisplayName("블랙 폰은 대각선 위로 이동할 수 없다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP_LEFT", "UP_RIGHT"})
        void blackPawnUpDirectionTest(Direction direction) {
            Path path = new Path(List.of(
                    new Step(direction, LocationState.ENEMY)
            ));
            assertThat(BLACK_PAWN.canMove(path)).isFalse();
        }

        @DisplayName("블랙 폰은 아래로 이동할 수 있다.")
        @Test
        void blackPawnDownDirectionTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN, LocationState.EMPTY)
            ));
            assertThat(BLACK_PAWN.canMove(path)).isTrue();
        }

        @DisplayName("움직인 적 없는 블랙 폰은 아래로 두 번 이동할 수 있다.")
        @Test
        void neverMovedBlackPawn_D_D_Test() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN, LocationState.EMPTY),
                    new Step(Direction.DOWN, LocationState.EMPTY)
            ));
            assertThat(BLACK_PAWN.canMove(path)).isTrue();
        }

        @DisplayName("움직인 적 있는 블랙 폰은 아래로 두 번 이동할 수 없다.")
        @Test
        void movedBlackPawn_D_D_Test() {
            BlackPawn movedPawn = new BlackPawn();
            movedPawn.move();
            Path path = new Path(List.of(
                    new Step(Direction.DOWN, LocationState.EMPTY),
                    new Step(Direction.DOWN, LocationState.EMPTY)
            ));

            assertThat(movedPawn.canMove(path)).isFalse();
        }

        @DisplayName("블랙 폰은 대각선 아래로 이동할 수 없다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"DOWN_LEFT", "DOWN_RIGHT"})
        void blackPawnDownDirectionTest(Direction direction) {
            Path path = new Path(List.of(
                    new Step(direction, LocationState.ENEMY)
            ));
            assertThat(BLACK_PAWN.canMove(path)).isTrue();
        }
    }

    @DisplayName("폰은 한 방향으로만 이동할 수 있다.")
    @Test
    void tooManyDirectionTest() {
        Path manyDirectionPath = new Path(List.of(
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.LEFT, LocationState.EMPTY)
        ));
        assertThat(BLACK_PAWN.canMove(manyDirectionPath))
                .isFalse();
    }

    @DisplayName("경로 중간에 기물이 위치한다면 움직일 수 없다.")
    @Test
    void pathHasPieceTest() {
        Path notEmptyPath = new Path(List.of(
                new Step(Direction.DOWN, LocationState.ALLY),
                new Step(Direction.DOWN, LocationState.ENEMY)
        ));

        assertThat(BLACK_PAWN.canMove(notEmptyPath))
                .isFalse();
    }

    @DisplayName("목적지에 아군이 존재한다면 움직일 수 없다.")
    @ParameterizedTest
    @EnumSource(Direction.class)
    void allyLocatedAtTargetTest(Direction direction) {
        Path manyDirectionPath = new Path(List.of(
                new Step(direction, LocationState.ALLY)
        ));

        assertThat(BLACK_PAWN.canMove(manyDirectionPath))
                .isFalse();
    }

    @DisplayName("최대 2칸까지 움직일 수 있다.")
    @Test
    void maxDistanceMoveTest() {
        Path manyDirectionPath = new Path(List.of(
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.DOWN, LocationState.EMPTY),
                new Step(Direction.DOWN, LocationState.EMPTY)
        ));

        assertThat(BLACK_PAWN.canMove(manyDirectionPath))
                .isFalse();
    }
}
