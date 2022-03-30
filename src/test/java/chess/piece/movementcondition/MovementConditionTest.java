package chess.piece.movementcondition;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Color;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.position.Position;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MovementConditionTest {

    @Test
    @DisplayName("이동 가능")
    void possible() {
        BaseMovementCondition condition = BaseMovementCondition.POSSIBLE;
        assertThat(condition.isPossibleMovement(new Position(A, EIGHT), new Position(B, SEVEN), Map.of()))
                .isTrue();
    }

    @Test
    @DisplayName("이동 불가능")
    void impossible() {
        BaseMovementCondition condition = BaseMovementCondition.IMPOSSIBLE;
        assertThat(condition.isPossibleMovement(new Position(A, EIGHT), new Position(B, SEVEN), Map.of()))
                .isFalse();
    }

    @ParameterizedTest
    @DisplayName("장애물이 있는 경우 이동 불가")
    @MethodSource("provideUnobstructed")
    void unobstructed(Position from, Position to, Position obstacle, boolean expect) {
        BaseMovementCondition condition = BaseMovementCondition.MUST_OBSTACLE_FREE;
        Map<Position, Piece> board = Map.of(obstacle, new Pawn(Color.WHITE));
        assertThat(condition.isPossibleMovement(from, to, board)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideUnobstructed() {
        return Stream.of(
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE), new Position(A, SIX), false),
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE), new Position(A, FOUR), true)
        );
    }

    @ParameterizedTest
    @DisplayName("포획할 기물이 있는 경우에만 이동 가능")
    @MethodSource("provideCatchable")
    void catchable(Position from, Position to, Map<Position, Piece> board, boolean expect) {
        BaseMovementCondition condition = BaseMovementCondition.MUST_CAPTURE_PIECE;
        assertThat(condition.isPossibleMovement(from, to, board)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideCatchable() {
        return Stream.of(
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE), Map.of(), false),
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE),
                        Map.of(new Position(A, FIVE), new Pawn(Color.WHITE)), true)
        );
    }

    @ParameterizedTest
    @DisplayName("기존에 위치한 기물과 장애물이 없는 경우에만 이동 가능")
    @MethodSource("provideUncatchableAndUnobstructed")
    void uncatcableAndUnobstructed(Position from, Position to, Map<Position, Piece> board, boolean expect) {
        MovementCondition condition = new MovementConditions(
                Set.of(BaseMovementCondition.MUST_EMPTY_DESTINATION, BaseMovementCondition.MUST_OBSTACLE_FREE));
        assertThat(condition.isPossibleMovement(from, to, board)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideUncatchableAndUnobstructed() {
        return Stream.of(
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE), Map.of(), true),
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE),
                        Map.of(new Position(A, FIVE), new Pawn(Color.WHITE)), false),
                Arguments.of(new Position(A, EIGHT), new Position(A, FIVE),
                        Map.of(new Position(A, SIX), new Pawn(Color.WHITE)), false)
        );
    }

}
