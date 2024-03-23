package domain.strategy;

import domain.position.Position;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static domain.Fixture.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightMoveStrategyTest {
    @DisplayName("나이트가 이동할 수 있는 출발지/도착지면 true 를 반환한다.")
    @MethodSource("validKnightMoveCase")
    @ParameterizedTest
    void isMovableTest(Position source, Position destination) {
        // Given
        Set<Position> otherPositions = Collections.emptySet();
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();

        // When
        boolean movable = knightMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isTrue();
    }

    private static Stream<Arguments> validKnightMoveCase() {
        return Stream.of(
                Arguments.of(D4, E6),
                Arguments.of(D4, F5),
                Arguments.of(D4, F3),
                Arguments.of(D4, E2),
                Arguments.of(D4, C2),
                Arguments.of(D4, B3),
                Arguments.of(D4, B5),
                Arguments.of(D4, C6)
        );
    }

    @DisplayName("나이트가 이동할 수 없는 출발지/도착지면 false 를 반환한다.")
    @MethodSource("inValidKnightMoveCase")
    @ParameterizedTest
    void isNotMovableTest(Position source, Position destination) {
        // Given
        Set<Position> otherPositions = Collections.emptySet();
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();

        // When
        boolean movable = knightMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> inValidKnightMoveCase() {
        return Stream.of(
                Arguments.of(D4, E5),
                Arguments.of(D4, F6),
                Arguments.of(D4, F2),
                Arguments.of(D4, E1),
                Arguments.of(D4, H3),
                Arguments.of(D4, C3),
                Arguments.of(D4, G1),
                Arguments.of(D4, D1)
        );
    }

}
