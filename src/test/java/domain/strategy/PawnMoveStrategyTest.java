package domain.strategy;

import domain.game.TeamColor;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static domain.Fixture.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveStrategyTest {
    @DisplayName("폰이 이동할 수 있는 출발/도착 위치면 true를 반환한다.")
    @MethodSource("isMovableCase")
    @ParameterizedTest
    void isMovableTest(TeamColor teamColor, Position source, Position destination) {
        // Given
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(teamColor);
        Set<Position> otherPositions = Set.of(D5, B4);

        // When
        boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isTrue();
    }

    private static Stream<Arguments> isMovableCase() {
        return Stream.of(
                Arguments.of(TeamColor.WHITE, C2, C4),
                Arguments.of(TeamColor.WHITE, C4, C5),
                Arguments.of(TeamColor.WHITE, C4, D5),
                Arguments.of(TeamColor.BLACK, C7, C5),
                Arguments.of(TeamColor.BLACK, C5, C4),
                Arguments.of(TeamColor.BLACK, C5, B4)
        );
    }

    @DisplayName("폰이 이동할 수 없는 출발/도착 위치면 false를 반환한다.")
    @MethodSource("isNotMovableCase")
    @ParameterizedTest
    void isNotMovableTest(TeamColor teamColor, Position source, Position destination) {
        // Given
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(teamColor);
        Set<Position> otherPositions = Collections.emptySet();

        // When
        boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> isNotMovableCase() {
        return Stream.of(
                Arguments.of(TeamColor.WHITE, C4, C6),
                Arguments.of(TeamColor.WHITE, C4, C3),
                Arguments.of(TeamColor.WHITE, C4, D4),
                Arguments.of(TeamColor.WHITE, C4, B5),
                Arguments.of(TeamColor.BLACK, C6, C4),
                Arguments.of(TeamColor.BLACK, C5, C6),
                Arguments.of(TeamColor.BLACK, C5, B5),
                Arguments.of(TeamColor.BLACK, C5, B4)
        );
    }

    @DisplayName("폰이 전진한 위치에 기물이 존재하면 이동할 수 없다.")
    @Test
    void isNotMovableToStraight() {
        // Given
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(TeamColor.WHITE);
        Position source = B2;
        Position destination = B3;
        Set<Position> otherPositions = new HashSet<>(Set.of(destination));

        // When
        boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isFalse();
    }

}
