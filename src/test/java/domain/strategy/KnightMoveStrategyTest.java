package domain.strategy;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static domain.Fixture.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightMoveStrategyTest {

    @DisplayName("나이트가 이동할 수 있는 출발지/도착지면 true를 반환한다.")
    @Test
    void isMovableTest() {
        // Given
        Position source = C2;
        Position destination = D4;
        Set<Position> otherPositions = Set.of(C3);
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();

        // When
        boolean movable = knightMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isTrue();
    }

    @DisplayName("나이트가 이동할 수 없는 출발지/도착지면 false를 반환한다.")
    @Test
    void isNotMovableTest() {
        // Given
        Position source = C2;
        Position destination = G5;
        Set<Position> otherPositions = Set.of(C3);
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();

        // When
        boolean movable = knightMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isFalse();
    }

}
