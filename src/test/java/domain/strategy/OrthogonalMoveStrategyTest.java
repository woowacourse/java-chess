package domain.strategy;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static domain.position.File.*;
import static domain.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class OrthogonalMoveStrategyTest {
    @DisplayName("출발지에서 목적지까지 상/하/좌/우 직선으로 이동 가능한 경우 true를 반환한다.")
    @MethodSource("validMove")
    @ParameterizedTest
    void isMovableTest(Position source, Position destination) {
        // Given
        OrthogonalMoveStrategy orthogonalMoveStrategy = new OrthogonalMoveStrategy();
        Set<Position> others = Collections.emptySet();

        // When
        boolean movable = orthogonalMoveStrategy.isMovable(source, destination, others);

        // Then
        assertThat(movable).isTrue();
    }

    private static Stream<Arguments> validMove() {
        return Stream.of(
                Arguments.of(new Position(B, THREE), new Position(B, SEVEN)),
                Arguments.of(new Position(D, THREE), new Position(F, THREE)),
                Arguments.of(new Position(D, FIVE), new Position(D, TWO)),
                Arguments.of(new Position(D, FIVE), new Position(A, FIVE))
        );
    }

    @DisplayName("출발지에서 목적지까지 상/하/좌/우 직선으로 이동 불가능한 경우 false를 반환한다.")
    @MethodSource("invalidMove")
    @ParameterizedTest
    void isNotMovableTest(Position source, Position destination, Set<Position> others) {
        // Given
        OrthogonalMoveStrategy orthogonalMoveStrategy = new OrthogonalMoveStrategy();

        // When
        boolean movable = orthogonalMoveStrategy.isMovable(source, destination, others);

        // Then
        assertThat(movable).isFalse();
    }

    private static Stream<Arguments> invalidMove() {
        return Stream.of(
                Arguments.of(new Position(B, THREE), new Position(B, SEVEN), Set.of(new Position(B, FIVE))),
                Arguments.of(new Position(D, THREE), new Position(F, THREE), Set.of(new Position(E, THREE))),
                Arguments.of(new Position(D, FIVE), new Position(D, TWO), Set.of(new Position(D, THREE))),
                Arguments.of(new Position(D, FIVE), new Position(A, FIVE), Set.of(new Position(C, FIVE)))
        );
    }
}
