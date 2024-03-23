package domain.strategy;

import domain.TeamColor;
import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveStrategyTest {
    @DisplayName("폰이 이동할 수 있는 출발/도착 위치면 true를 반환한다.")
    @MethodSource("isMovableCase")
    @ParameterizedTest
    void isMovableTest(TeamColor teamColor, Position source, Position destination) {
        // Given
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(teamColor);
        Set<Position> otherPositions = Set.of(new Position(File.D, Rank.FIVE), new Position(File.B, Rank.FOUR));

        // When
        boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isTrue();
    }

    private static Stream<Arguments> isMovableCase() {
        return Stream.of(
                Arguments.of(TeamColor.WHITE, new Position(File.C, Rank.TWO), new Position(File.C, Rank.FOUR)),
                Arguments.of(TeamColor.WHITE, new Position(File.C, Rank.FOUR), new Position(File.C, Rank.FIVE)),
                Arguments.of(TeamColor.WHITE, new Position(File.C, Rank.FOUR), new Position(File.D, Rank.FIVE)),
                Arguments.of(TeamColor.BLACK, new Position(File.C, Rank.SEVEN), new Position(File.C, Rank.FIVE)),
                Arguments.of(TeamColor.BLACK, new Position(File.C, Rank.FIVE), new Position(File.C, Rank.FOUR)),
                Arguments.of(TeamColor.BLACK, new Position(File.C, Rank.FIVE), new Position(File.B, Rank.FOUR))
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
                Arguments.of(TeamColor.WHITE, new Position(File.C, Rank.FOUR), new Position(File.C, Rank.SIX)),
                Arguments.of(TeamColor.WHITE, new Position(File.C, Rank.FOUR), new Position(File.C, Rank.THREE)),
                Arguments.of(TeamColor.WHITE, new Position(File.C, Rank.FOUR), new Position(File.D, Rank.FOUR)),
                Arguments.of(TeamColor.WHITE, new Position(File.C, Rank.FOUR), new Position(File.B, Rank.FIVE)),
                Arguments.of(TeamColor.BLACK, new Position(File.C, Rank.SIX), new Position(File.C, Rank.FOUR)),
                Arguments.of(TeamColor.BLACK, new Position(File.C, Rank.FIVE), new Position(File.C, Rank.SIX)),
                Arguments.of(TeamColor.BLACK, new Position(File.C, Rank.FIVE), new Position(File.B, Rank.FIVE)),
                Arguments.of(TeamColor.BLACK, new Position(File.C, Rank.FIVE), new Position(File.B, Rank.FOUR))
        );
    }

    @DisplayName("폰이 전진한 위치에 기물이 존재하면 이동할 수 없다.")
    @Test
    void isNotMovableToStraight() {
        // Given
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(TeamColor.WHITE);
        Position source = new Position(File.B, Rank.TWO);
        Position destination = new Position(File.B, Rank.THREE);
        Set<Position> otherPositions = new HashSet<>(Set.of(destination));

        // When
        boolean movable = pawnMoveStrategy.isMovable(source, destination, otherPositions);

        // Then
        assertThat(movable).isFalse();
    }

}
