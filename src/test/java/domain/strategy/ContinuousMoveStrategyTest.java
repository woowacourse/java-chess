package domain.strategy;

import domain.board.Position;
import domain.piece.CommonMovementDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static domain.board.File.*;
import static domain.board.Rank.*;
import static domain.piece.CommonMovementDirection.*;
import static org.assertj.core.api.Assertions.assertThat;

class ContinuousMoveStrategyTest {
    private static final Set<CommonMovementDirection> orthogonalVectors = Set.of(UP, RIGHT, DOWN, LEFT);
    private static final Set<CommonMovementDirection> diagonalVectors = Set.of(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    private static final Set<CommonMovementDirection> omnidirectionalVectors = Set.of(UP, RIGHT, DOWN, LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    private static final ContinuousMoveStrategy orthogonalMoveStrategy = new ContinuousMoveStrategy(orthogonalVectors, 8);
    private static final ContinuousMoveStrategy diagonalMoveStrategy = new ContinuousMoveStrategy(diagonalVectors, 8);

    @DisplayName("출발지에서 목적지까지 상/하/좌/우 직선으로 이동 가능한 경우 true를 반환한다.")
    @MethodSource("orthogonalValidMove")
    @ParameterizedTest
    void orthogonalMovableTest(Position source, Position destination) {
        // Given
        Set<Position> others = Collections.emptySet();

        // When
        boolean movable = orthogonalMoveStrategy.isMovable(source, destination, others);

        // Then
        assertThat(movable).isTrue();
    }

    private static Stream<Arguments> orthogonalValidMove() {
        return Stream.of(
                Arguments.of(new Position(B, THREE), new Position(B, SEVEN)),
                Arguments.of(new Position(D, THREE), new Position(F, THREE)),
                Arguments.of(new Position(D, FIVE), new Position(D, TWO)),
                Arguments.of(new Position(D, FIVE), new Position(A, FIVE))
        );
    }

    @DisplayName("출발지에서 목적지까지 상/하/좌/우 직선으로 이동 불가능한 경우 false를 반환한다.")
    @MethodSource("orthogonalInvalidMove")
    @ParameterizedTest
    void notOrthogonalMovableTest(Position source, Position destination, Set<Position> others) {
        // When
        boolean movable = orthogonalMoveStrategy.isMovable(source, destination, others);

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> orthogonalInvalidMove() {
        return Stream.of(
                Arguments.of(new Position(B, THREE), new Position(B, SEVEN), Set.of(new Position(B, FIVE))),
                Arguments.of(new Position(D, THREE), new Position(F, THREE), Set.of(new Position(E, THREE))),
                Arguments.of(new Position(D, FIVE), new Position(D, TWO), Set.of(new Position(D, THREE))),
                Arguments.of(new Position(D, FIVE), new Position(A, FIVE), Set.of(new Position(C, FIVE))),
                Arguments.of(new Position(D, FIVE), new Position(B, THREE), Collections.emptySet())
        );
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동 가능한 경우 true를 반환한다.")
    @MethodSource("diagonalValidMove")
    @ParameterizedTest
    void diagonalMovableTest(Position source, Position destination) {
        // Given
        Set<Position> others = Collections.emptySet();

        // When
        boolean movable = diagonalMoveStrategy.isMovable(source, destination, others);

        // Then
        assertThat(movable).isTrue();
    }

    private static Stream<Arguments> diagonalValidMove() {
        return Stream.of(
                Arguments.of(new Position(B, THREE), new Position(F, SEVEN)),
                Arguments.of(new Position(D, THREE), new Position(F, ONE)),
                Arguments.of(new Position(D, FIVE), new Position(A, TWO)),
                Arguments.of(new Position(D, FIVE), new Position(C, SIX))
        );
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동 불가능한 경우 false를 반환한다.")
    @MethodSource("diagonalInvalidMove")
    @ParameterizedTest
    void notDiagonalMovableTest(Position source, Position destination, Set<Position> others) {
        // When
        boolean movable = diagonalMoveStrategy.isMovable(source, destination, others);

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> diagonalInvalidMove() {
        return Stream.of(
                Arguments.of(new Position(B, THREE), new Position(F, SEVEN), Set.of(new Position(D, FIVE))),
                Arguments.of(new Position(D, THREE), new Position(F, ONE), Set.of(new Position(E, TWO))),
                Arguments.of(new Position(D, FIVE), new Position(A, TWO), Set.of(new Position(B, THREE))),
                Arguments.of(new Position(D, FIVE), new Position(B, SEVEN), Set.of(new Position(C, SIX))),
                Arguments.of(new Position(D, FIVE), new Position(H, FIVE), Collections.emptySet())
        );
    }


    @DisplayName("출발지에서 목적지까지 정해진 거리 이내에 이동이 불가능한 경우 false를 반환한다.")
    @MethodSource("boundedMove")
    @ParameterizedTest
    void notDiagonalMovableTest(Position source, Position destination, int moveBound) {
        // When
        ContinuousMoveStrategy limitedBoundMoveStrategy = new ContinuousMoveStrategy(omnidirectionalVectors, moveBound);
        boolean movable = limitedBoundMoveStrategy.isMovable(source, destination, Collections.emptySet());

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> boundedMove() {
        return Stream.of(
                Arguments.of(new Position(D, FIVE), new Position(F, SEVEN), 1),
                Arguments.of(new Position(D, FIVE), new Position(G, EIGHT), 2),
                Arguments.of(new Position(A, EIGHT), new Position(E, FOUR), 3),
                Arguments.of(new Position(A, EIGHT), new Position(A, THREE), 4),
                Arguments.of(new Position(A, EIGHT), new Position(H, ONE), 5),
                Arguments.of(new Position(A, EIGHT), new Position(H, EIGHT), 6)
        );
    }
}
