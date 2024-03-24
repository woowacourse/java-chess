package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Direction;
import chess.domain.board.Route;
import chess.domain.board.SquareState;
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

    @DisplayName("폰은 한 방향으로만 이동할 수 있다.")
    @Test
    void tooManyDirectionTest() {
        Route manyDirectionRoute = new Route(
                List.of(Direction.UP, Direction.LEFT),
                List.of(SquareState.EMPTY, SquareState.EMPTY)
        );
        assertThat(WHITE_PAWN.canMove(manyDirectionRoute))
                .isFalse();
    }

    @DisplayName("경로 중간에 기물이 위치한다면 움직일 수 없다.")
    @Test
    void pathHasPieceTest() {
        Route notEmptyRoute = new Route(
                List.of(Direction.UP, Direction.UP),
                List.of(SquareState.ALLY, SquareState.ENEMY)
        );

        assertThat(WHITE_PAWN.canMove(notEmptyRoute))
                .isFalse();
    }

    @DisplayName("목적지에 아군이 존재한다면 움직일 수 없다.")
    @ParameterizedTest
    @EnumSource(Direction.class)
    void allyLocatedAtTargetTest(Direction direction) {
        Route manyDirectionRoute = new Route(
                List.of(direction),
                List.of(SquareState.ALLY)
        );

        assertThat(WHITE_PAWN.canMove(manyDirectionRoute))
                .isFalse();
    }

    @DisplayName("최대 2칸까지 움직일 수 있다.")
    @Test
    void maxDistanceMoveTest() {
        Route manyDirectionRoute = new Route(
                List.of(Direction.UP, Direction.UP, Direction.UP),
                List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY)
        );

        assertThat(WHITE_PAWN.canMove(manyDirectionRoute))
                .isFalse();
    }

    @DisplayName("반대 방향으로 이동할 수 없다.")
    @Nested
    class ForwardTest {
        @DisplayName("화이트 폰은 아래로 이동할 수 없다.")
        @Test
        void whitePawnDownDirectionTest() {
            Route route = new Route(
                    List.of(Direction.DOWN),
                    List.of(SquareState.EMPTY)
            );
            assertThat(WHITE_PAWN.canMove(route)).isFalse();
        }

        @DisplayName("화이트 폰은 대각선 아래로 이동할 수 없다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"DOWN_LEFT", "DOWN_RIGHT"})
        void whitePawnDownDirectionTest(Direction direction) {
            Route route = new Route(
                    List.of(direction),
                    List.of(SquareState.ENEMY)
            );
            assertThat(WHITE_PAWN.canMove(route)).isFalse();
        }

        @DisplayName("화이트 폰은 위로 이동할 수 있다.")
        @Test
        void whitePawnUpDirectionTest() {
            Route route = new Route(
                    List.of(Direction.UP),
                    List.of(SquareState.EMPTY)
            );
            assertThat(WHITE_PAWN.canMove(route)).isTrue();
        }

        @DisplayName("움직인 적 없는 화이트 폰은 위로 두 번 이동할 수 있다.")
        @Test
        void neverMovedWhitePawn_U_U_Test() {
            Route route = new Route(
                    List.of(Direction.UP, Direction.UP),
                    List.of(SquareState.EMPTY, SquareState.EMPTY)
            );
            assertThat(WHITE_PAWN.canMove(route)).isTrue();
        }

        @DisplayName("움직인 적 있는 화이트 폰은 위로 두 번 이동할 수 없다.")
        @Test
        void movedWhitePawn_U_U_Test() {
            Route route = new Route(
                    List.of(Direction.UP, Direction.UP),
                    List.of(SquareState.EMPTY, SquareState.EMPTY)
            );
            WHITE_PAWN.canMove(route);
            assertThat(WHITE_PAWN.canMove(route)).isFalse();
        }

        @DisplayName("화이트 폰은 대각선 위로 이동할 수 없다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP_LEFT", "UP_RIGHT"})
        void whitePawnUpDirectionTest(Direction direction) {
            Route route = new Route(
                    List.of(direction),
                    List.of(SquareState.ENEMY)
            );
            assertThat(WHITE_PAWN.canMove(route)).isTrue();
        }
    }

}
