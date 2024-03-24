package domain.strategy;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static domain.Fixture.Vectors.*;
import static domain.Fixture.Positions.*;
import static domain.Fixture.Strategies.*;
import static org.assertj.core.api.Assertions.assertThat;

class ContinuousMoveStrategyTest {
    @Nested
    @DisplayName("룩 - 상하좌우 방향으로 직선 이동 가능")
    class RookTest {
        @DisplayName("출발지에서 목적지까지 상/하/좌/우 직선으로 이동 가능한 경우 true 를 반환한다.")
        @MethodSource("orthogonalValidMove")
        @ParameterizedTest
        void orthogonalMovableTest(Position source, Position destination) {
            // Given
            Set<Position> others = Collections.emptySet();

            // When
            boolean movable = ROOK_MOVE_STRATEGY.isMovable(source, destination, others);

            // Then
            assertThat(movable).isTrue();
        }

        private static Stream<Arguments> orthogonalValidMove() {
            return Stream.of(
                    Arguments.of(B3, B7),
                    Arguments.of(D3, F3),
                    Arguments.of(D5, D2),
                    Arguments.of(D5, A5)
            );
        }

        @DisplayName("출발지에서 목적지까지 상/하/좌/우 직선으로 이동 불가능한 경우 false 를 반환한다.")
        @MethodSource("orthogonalInvalidMove")
        @ParameterizedTest
        void notOrthogonalMovableTest(Position source, Position destination, Set<Position> others) {
            // When
            boolean movable = ROOK_MOVE_STRATEGY.isMovable(source, destination, others);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> orthogonalInvalidMove() {
            return Stream.of(
                    Arguments.of(B3, B7, Set.of(B5)),
                    Arguments.of(D3, F3, Set.of(E3)),
                    Arguments.of(D5, D2, Set.of(D3)),
                    Arguments.of(D5, A5, Set.of(C5)),
                    Arguments.of(D5, B4, Collections.emptySet()),
                    Arguments.of(D5, B3, Collections.emptySet())
            );
        }
    }

    @Nested
    @DisplayName("비숍 - 대각선 4방향으로 직선 이동 가능")
    class BishopTest {
        @DisplayName("출발지에서 목적지까지 대각선으로 이동 가능한 경우 true 를 반환한다.")
        @MethodSource("diagonalValidMove")
        @ParameterizedTest
        void diagonalMovableTest(Position source, Position destination) {
            // Given
            Set<Position> others = Collections.emptySet();

            // When
            boolean movable = BISHOP_MOVE_STRATEGY.isMovable(source, destination, others);

            // Then
            assertThat(movable).isTrue();
        }

        private static Stream<Arguments> diagonalValidMove() {
            return Stream.of(
                    Arguments.of(B3, F7),
                    Arguments.of(D3, F1),
                    Arguments.of(D5, A2),
                    Arguments.of(D5, C6)
            );
        }

        @DisplayName("출발지에서 목적지까지 대각선으로 이동 불가능한 경우 false 를 반환한다.")
        @MethodSource("diagonalInvalidMove")
        @ParameterizedTest
        void notDiagonalMovableTest(Position source, Position destination, Set<Position> others) {
            // When
            boolean movable = BISHOP_MOVE_STRATEGY.isMovable(source, destination, others);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> diagonalInvalidMove() {
            return Stream.of(
                    Arguments.of(B3, F3, Set.of(D5)),
                    Arguments.of(D3, F1, Set.of(E2)),
                    Arguments.of(D5, A2, Set.of(B3)),
                    Arguments.of(D5, B7, Set.of(C6)),
                    Arguments.of(D5, H5, Collections.emptySet()),
                    Arguments.of(D5, F8, Collections.emptySet())
            );
        }
    }

    @Nested
    @DisplayName("퀸 - 상하좌우 4방향, 대각선 4방향으로 직선 이동 가능")
    class QueenTest {
        @DisplayName("출발지에서 목적지까지 8방위로 이동 가능한 경우 true 를 반환한다.")
        @MethodSource("omnidirectionalValidMove")
        @ParameterizedTest
        void omnidirectionalMovableTest(Position source, Position destination) {
            // Given
            Set<Position> others = Collections.emptySet();

            // When
            boolean movable = QUEEN_MOVE_STRATEGY.isMovable(source, destination, others);

            // Then
            assertThat(movable).isTrue();
        }

        private static Stream<Arguments> omnidirectionalValidMove() {
            return Stream.of(
                    Arguments.of(B3, F7),
                    Arguments.of(D3, F1),
                    Arguments.of(D5, A2),
                    Arguments.of(D5, C6),
                    Arguments.of(B3, F7),
                    Arguments.of(D3, F1),
                    Arguments.of(D5, A2),
                    Arguments.of(D5, C6)
            );
        }

        @DisplayName("출발지에서 목적지까지 8방위로 이동 불가능한 경우 false 를 반환한다.")
        @MethodSource("omnidirectionalInvalidMove")
        @ParameterizedTest
        void notDiagonalMovableTest(Position source, Position destination, Set<Position> others) {
            // When
            boolean movable = QUEEN_MOVE_STRATEGY.isMovable(source, destination, others);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> omnidirectionalInvalidMove() {
            return Stream.of(
                    Arguments.of(B3, F4, Set.of(D5)),
                    Arguments.of(D3, F1, Set.of(E2)),
                    Arguments.of(D5, A2, Set.of(B3)),
                    Arguments.of(D5, B7, Set.of(C6)),
                    Arguments.of(D5, H4, Collections.emptySet()),
                    Arguments.of(D5, F8, Collections.emptySet()),
                    Arguments.of(B3, B7, Set.of(B5)),
                    Arguments.of(D3, F3, Set.of(E3)),
                    Arguments.of(D5, D2, Set.of(D3)),
                    Arguments.of(D5, A5, Set.of(C5)),
                    Arguments.of(D5, B4, Collections.emptySet()),
                    Arguments.of(D5, A3, Collections.emptySet())
            );
        }
    }

    @DisplayName("출발지에서 목적지까지 정해진 거리 이내에 이동이 불가능한 경우 false 를 반환한다.")
    @MethodSource("boundedMove")
    @ParameterizedTest
    void notDiagonalMovableTest(Position source, Position destination, int moveBound) {
        // When
        ContinuousMoveStrategy limitedBoundMoveStrategy = new ContinuousMoveStrategy(OMNIDIRECTIONAL_VECTORS, moveBound);
        boolean movable = limitedBoundMoveStrategy.isMovable(source, destination, Collections.emptySet());

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> boundedMove() {
        return Stream.of(
                Arguments.of(D5, F3, 1),
                Arguments.of(D5, G8, 2),
                Arguments.of(A8, E4, 3),
                Arguments.of(A8, A3, 4),
                Arguments.of(A8, H1, 5),
                Arguments.of(A8, H8, 6)
        );
    }
}
