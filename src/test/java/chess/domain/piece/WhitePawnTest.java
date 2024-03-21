package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Direction;
import chess.domain.board.Path;
import chess.domain.board.SquareState;
import chess.domain.board.Step;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class WhitePawnTest {
    private WhitePawn WHITE_PAWN;

    @BeforeEach
    void beforeEach() {
        WHITE_PAWN = new WhitePawn();
    }

    @DisplayName("반대 방향으로 이동할 수 없다.")
    @Nested
    class ForwardTest {
        @DisplayName("화이트 폰은 아래로 이동할 수 없다.")
        @Test
        void whitePawnDownDirectionTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN, SquareState.EMPTY)
            ));
            assertThat(WHITE_PAWN.canMove(path)).isFalse();
        }

        @DisplayName("화이트 폰은 대각선 아래로 이동할 수 없다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"DOWN_LEFT", "DOWN_RIGHT"})
        void whitePawnDownDirectionTest(Direction direction) {
            Path path = new Path(List.of(
                    new Step(direction, SquareState.ENEMY)
            ));
            assertThat(WHITE_PAWN.canMove(path)).isFalse();
        }

        @DisplayName("화이트 폰은 위로 이동할 수 있다.")
        @Test
        void whitePawnUpDirectionTest() {
            Path path = new Path(List.of(
                    new Step(Direction.UP, SquareState.EMPTY)
            ));
            assertThat(WHITE_PAWN.canMove(path)).isTrue();
        }

        //TODO 테스트 이름 변경
        @DisplayName("움직인 적 없는 화이트 폰은 위로 두 번 이동할 수 있다.")
        @Test
        void blackPawnDownDownDirectionTest() {
            Path path = new Path(List.of(
                    new Step(Direction.UP, SquareState.EMPTY),
                    new Step(Direction.UP, SquareState.EMPTY)
            ));
            assertThat(WHITE_PAWN.canMove(path)).isTrue();
        }
        //TODO 테스트 이름 변경

        @DisplayName("움직인 적 있는 화이트 폰은 위로 두 번 이동할 수 없다.")
        @Test
        void blackPawnDownDownDirectionTest2() {
            Path path = new Path(List.of(
                    new Step(Direction.UP, SquareState.EMPTY),
                    new Step(Direction.UP, SquareState.EMPTY)
            ));
            WHITE_PAWN.canMove(path);
            assertThat(WHITE_PAWN.canMove(path)).isFalse();
        }

        @DisplayName("화이트 폰은 대각선 위로 이동할 수 없다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP_LEFT", "UP_RIGHT"})
        void whitePawnUpDirectionTest(Direction direction) {
            Path path = new Path(List.of(
                    new Step(direction, SquareState.ENEMY)
            ));
            assertThat(WHITE_PAWN.canMove(path)).isTrue();
        }
    }

    @DisplayName("폰은 한 방향으로만 이동할 수 있다.")
    @Test
    void tooManyDirectionTest() {
        Path manyDirectionPath = new Path(List.of(
                new Step(Direction.UP, SquareState.EMPTY),
                new Step(Direction.LEFT, SquareState.EMPTY)
        ));
        assertThat(WHITE_PAWN.canMove(manyDirectionPath))
                .isFalse();
    }

    @DisplayName("경로 중간에 기물이 위치한다면 움직일 수 없다.")
    @Test
    void pathHasPieceTest() {
        Path notEmptyPath = new Path(List.of(
                new Step(Direction.UP, SquareState.ALLY),
                new Step(Direction.UP, SquareState.ENEMY)
        ));

        assertThat(WHITE_PAWN.canMove(notEmptyPath))
                .isFalse();
    }

    @DisplayName("목적지에 아군이 존재한다면 움직일 수 없다.")
    @ParameterizedTest
    @EnumSource(Direction.class)
    void allyLocatedAtTargetTest(Direction direction) {
        Path manyDirectionPath = new Path(List.of(
                new Step(direction, SquareState.ALLY)
        ));

        assertThat(WHITE_PAWN.canMove(manyDirectionPath))
                .isFalse();
    }

    @DisplayName("최대 2칸까지 움직일 수 있다.")
    @Test
    void maxDistanceMoveTest() {
        Path manyDirectionPath = new Path(List.of(
                new Step(Direction.UP, SquareState.EMPTY),
                new Step(Direction.UP, SquareState.EMPTY),
                new Step(Direction.UP, SquareState.EMPTY)
        ));

        assertThat(WHITE_PAWN.canMove(manyDirectionPath))
                .isFalse();
    }

}
