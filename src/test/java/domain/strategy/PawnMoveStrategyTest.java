package domain.strategy;

import domain.Fixture;
import domain.game.TeamColor;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static domain.game.TeamColor.*;
import static domain.Fixture.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveStrategyTest {
    @Nested
    @DisplayName("한 칸 이동")
    class MoveOneStepForward {
        @DisplayName("흰색 폰은 Rank 증가 방향, 검은색 폰은 Rank 감소 방향으로 이동할 수 있다.")
        @MethodSource("isMovableCase")
        @ParameterizedTest
        void isMovableTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = Collections.emptySet();

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isTrue();
        }

        private static Stream<Arguments> isMovableCase() {
            return Stream.of(
                    Arguments.of(WHITE, C2, C3),
                    Arguments.of(WHITE, C3, C4),
                    Arguments.of(BLACK, C7, C6),
                    Arguments.of(BLACK, C6, C5)
            );
        }

        @DisplayName("폰은 색깔에 맞지 않는 방향으로 이동(후진)할 수 없다.")
        @MethodSource("invalidMovableCase")
        @ParameterizedTest
        void isNotMovableTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = Collections.emptySet();

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> invalidMovableCase() {
            return Stream.of(
                    Arguments.of(WHITE, C3, C2),
                    Arguments.of(WHITE, C4, C3),
                    Arguments.of(BLACK, C6, C7),
                    Arguments.of(BLACK, C5, C6)
            );
        }

        @DisplayName("폰이 전진한 위치에 기물이 존재하면 이동할 수 없다.")
        @MethodSource("notMovableToStraightCase")
        @ParameterizedTest
        void isNotMovableToStraightTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = new HashSet<>(Set.of(destination));

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> notMovableToStraightCase() {
            return Stream.of(
                    Arguments.of(WHITE, A2, A3),
                    Arguments.of(BLACK, D7, D5)
            );
        }
    }

    @Nested
    @DisplayName("두 칸 이동")
    class MoveTwoStepForward {
        @DisplayName("각 폰이 초기 위치에 있을 때, 두 칸 앞으로 이동할 수 있다.")
        @MethodSource("isMovableCase")
        @ParameterizedTest
        void isMovableTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = Collections.emptySet();

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isTrue();
        }

        private static Stream<Arguments> isMovableCase() {
            return Stream.of(
                    Arguments.of(WHITE, C2, C4),
                    Arguments.of(BLACK, C7, C5)
            );
        }

        @DisplayName("폰의 위치가 초기 위치가 아닌 경우, 두 칸 이동할 수 없다.")
        @MethodSource("invalidMovableCase")
        @ParameterizedTest
        void isNotMovableTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = Collections.emptySet();

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> invalidMovableCase() {
            return Stream.of(
                    Arguments.of(WHITE, C3, C5),
                    Arguments.of(BLACK, C6, C4)
            );
        }

        @DisplayName("초기 위치에서 이동하더라도 다른 기물을 건너뛰어서 두 칸을 이동할 수는 없다.")
        @MethodSource("notMovableTwoStepWhenBlockedByOtherPiece")
        @ParameterizedTest
        void notMovableTwoStepWhenBlockedByOtherPieceTest(TeamColor teamColor, Position source, Position destination, Set<Position> otherPositions) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> notMovableTwoStepWhenBlockedByOtherPiece() {
            return Stream.of(
                    Arguments.of(WHITE, A2, A4, Set.of(A3)),
                    Arguments.of(BLACK, D7, D5, Set.of(D6))
            );
        }

        @DisplayName("폰이 전진한 위치에 기물이 존재하면 이동할 수 없다.")
        @MethodSource("notMovableToStraightCase")
        @ParameterizedTest
        void isNotMovableToStraightTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = new HashSet<>(Set.of(destination));

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> notMovableToStraightCase() {
            return Stream.of(
                    Arguments.of(WHITE, A2, A4),
                    Arguments.of(WHITE, A2, A3),
                    Arguments.of(BLACK, D7, D5),
                    Arguments.of(BLACK, D7, D6)
            );
        }
    }

    @Nested
    @DisplayName("대각선 이동")
    class MoveCross {
        @DisplayName("목적지에 공격할 기물이 존재하는 경우, 색깔별 올바른 방향으로 대각선 한 칸 앞으로 이동할 수 있다.")
        @MethodSource("forwardDiagonalCase")
        @ParameterizedTest
        void isMovableTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = Set.of(destination);

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isTrue();
        }

        @DisplayName("목적지에 공격할 기물이 존재하지 않는 경우, 대각선으로 이동할 수 없다.")
        @MethodSource("forwardDiagonalCase")
        @ParameterizedTest
        void isNotMovableTest(TeamColor teamColor, Position source, Position destination) {
            // Given
            PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
            Set<Position> otherPositions = Collections.emptySet();

            // When
            boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

            // Then
            assertThat(movable).isFalse();
        }

        private static Stream<Arguments> forwardDiagonalCase() {
            return Stream.of(
                    Arguments.of(WHITE, C2, B3),
                    Arguments.of(WHITE, C2, D3),
                    Arguments.of(BLACK, C7, B6),
                    Arguments.of(BLACK, C7, D6)
            );
        }
    }

    @DisplayName("한 칸/두 칸 앞 또는 대각선 앞쪽 한 칸 이외에는 이동할 수 없다.")
    @MethodSource("invalidMovableCase")
    @ParameterizedTest
    void isNotMovableTest(TeamColor teamColor, Position source, Position destination) {
        // Given
        PawnMoveStrategy pawnMoveStrategy = Fixture.pawnMoveStrategyOf(teamColor);
        Set<Position> otherPositions = Collections.emptySet();

        // When
        boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> invalidMovableCase() {
        return Stream.of(
                Arguments.of(WHITE, C4, B4),
                Arguments.of(WHITE, C4, D4),
                Arguments.of(WHITE, C4, H4),
                Arguments.of(WHITE, C4, A4),
                Arguments.of(WHITE, C4, G8),
                Arguments.of(WHITE, C4, F6)
        );
    }
}
