package direction;

import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @DisplayName("Position source와 target으로 이동 방향 확인")
    @ParameterizedTest
    @MethodSource("generatePositionsAndMoveRule")
    void 방향찾기(String sourceInput, String targetInput, Direction direction) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetInput);

        assertThat(Direction.getDirection(source, target)).isEqualTo(direction);
    }

    static Stream<Arguments> generatePositionsAndMoveRule() {
        return Stream.of(
                Arguments.of("a1", "a8", Direction.NORTH),
                Arguments.of("a8", "a1", Direction.SOUTH),
                Arguments.of("a1", "h1", Direction.EAST),
                Arguments.of("h1", "a1", Direction.WEST),
                Arguments.of("a8", "h1", Direction.SOUTH_EAST),
                Arguments.of("h8", "a1", Direction.SOUTH_WEST),
                Arguments.of("h1", "a8", Direction.NORTH_WEST),
                Arguments.of("a1", "h8", Direction.NORTH_EAST));
    }

    @DisplayName( "Postition source와 target 사이의 route: top, down")
    @ParameterizedTest
    @MethodSource("generateTopDownPositions")
    void topDownTest(String sourceInput, String targetValue, Direction direction) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = direction.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.A, Column.TWO),
                Positions.of(Row.A, Column.THREE),
                Positions.of(Row.A, Column.FOUR),
                Positions.of(Row.A, Column.FIVE),
                Positions.of(Row.A, Column.SIX),
                Positions.of(Row.A, Column.SEVEN)};

        assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateTopDownPositions() {
        return Stream.of(
                Arguments.of("a1", "a8", Direction.NORTH),
                Arguments.of("a8", "a1", Direction.SOUTH));
    }

    @DisplayName( "Postition source와 target 사이의 routes: left, right")
    @ParameterizedTest
    @MethodSource("generateLeftRightPositions")
    void leftRightTest(String sourceInput, String targetValue, Direction direction) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = direction.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.B, Column.ONE),
                Positions.of(Row.C, Column.ONE),
                Positions.of(Row.D, Column.ONE),
                Positions.of(Row.E, Column.ONE),
                Positions.of(Row.F, Column.ONE),
                Positions.of(Row.G, Column.ONE)};

        assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateLeftRightPositions() {
        return Stream.of(
                Arguments.of("a1", "h1", Direction.WEST),
                Arguments.of("h1", "a1", Direction.EAST));
    }

    @DisplayName( "Postition source와 target 사이의 routes: '\' 대각선")
    @ParameterizedTest
    @MethodSource("generateTopLeftDownRightPositions")
    void diagonalTopLeftDownRightTest(String sourceInput, String targetValue, Direction direction) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = direction.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.B, Column.TWO),
                Positions.of(Row.C, Column.THREE),
                Positions.of(Row.D, Column.FOUR),
                Positions.of(Row.E, Column.FIVE),
                Positions.of(Row.F, Column.SIX),
                Positions.of(Row.G, Column.SEVEN)};

        assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateTopLeftDownRightPositions() {
        return Stream.of(
                Arguments.of("a1", "h8", Direction.NORTH_EAST),
                Arguments.of("h8", "a1", Direction.SOUTH_WEST));
    }

    @DisplayName( "Postition source와 target 사이의 routes: / 대각선")
    @ParameterizedTest
    @MethodSource("generateTopRightDownLeftPositions")
    void diagonalTopRightDownLeftTest(String sourceInput, String targetValue, Direction direction) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = direction.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.B, Column.TWO),
                Positions.of(Row.C, Column.THREE),
                Positions.of(Row.D, Column.FOUR),
                Positions.of(Row.E, Column.FIVE),
                Positions.of(Row.F, Column.SIX),
                Positions.of(Row.G, Column.SEVEN)};

        assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateTopRightDownLeftPositions() {
        return Stream.of(
                Arguments.of("h1", "a8", Direction.NORTH_WEST),
                Arguments.of("a8", "h1", Direction.SOUTH_EAST));
    }
}
