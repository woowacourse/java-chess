import chess.domain.moverules.MoveRule;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MoveRuleTest {
    @DisplayName( "top/down 이동했을 경우 source와 target 사이의 position들 확인")
    @ParameterizedTest
    @MethodSource("generateTopDownPositions")
    void opDownTest(String sourceInput, String targetValue, MoveRule moveRule ) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = moveRule.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.A, Column.TWO),
                Positions.of(Row.A, Column.THREE),
                Positions.of(Row.A, Column.FOUR),
                Positions.of(Row.A, Column.FIVE),
                Positions.of(Row.A, Column.SIX),
                Positions.of(Row.A, Column.SEVEN)};

        Assertions.assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateTopDownPositions() {
        return Stream.of(
                Arguments.of("a1", "a8", MoveRule.TOP),
                Arguments.of("a8", "a1",MoveRule.DOWN));
    }

    @DisplayName("Left/Right 이동했을 경우 source와 target 사이의 position들 확인")
    @ParameterizedTest
    @MethodSource("generateLeftRightPositions")
    void leftRightTest(String sourceInput, String targetValue, MoveRule moveRule ) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = moveRule.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.B, Column.ONE),
                Positions.of(Row.C, Column.ONE),
                Positions.of(Row.D, Column.ONE),
                Positions.of(Row.E, Column.ONE),
                Positions.of(Row.F, Column.ONE),
                Positions.of(Row.G, Column.ONE)};

        Assertions.assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateLeftRightPositions() {
        return Stream.of(
                Arguments.of("a1", "h1", MoveRule.LEFT),
                Arguments.of("h1", "a1",MoveRule.RIGHT));
    }

    @DisplayName("왼쪽 아래, 오른쪽 위 이동했을 경우 source와 target 사이의 position들 확인")
    @ParameterizedTest
    @MethodSource("generateTopLeftDownRightPositions")
    void diagonalTopLeftDownRightTest(String sourceInput, String targetValue, MoveRule moveRule ) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = moveRule.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.B, Column.TWO),
                Positions.of(Row.C, Column.THREE),
                Positions.of(Row.D, Column.FOUR),
                Positions.of(Row.E, Column.FIVE),
                Positions.of(Row.F, Column.SIX),
                Positions.of(Row.G, Column.SEVEN)};

        Assertions.assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateTopLeftDownRightPositions() {
        return Stream.of(
                Arguments.of("a1", "h8", MoveRule.DIAGONAL_TOP_RIGHT),
                Arguments.of("h8", "a1",MoveRule.DIAGONAL_DOWN_LEFT));
    }

    @DisplayName("왼쪽 위, 오른쪽 아래 이동했을 경우 source와 target 사이의 position들 확인")
    @ParameterizedTest
    @MethodSource("generateTopRightDownLeftPositions")
    void diagonalTopRightDownLeftTest(String sourceInput, String targetValue, MoveRule moveRule ) {
        Position source = Positions.of(sourceInput);
        Position target = Positions.of(targetValue);

        List<Position> actual = moveRule.getPositionsBetween(source, target);

        Position[] expected = {Positions.of(Row.B, Column.TWO),
                Positions.of(Row.C, Column.THREE),
                Positions.of(Row.D, Column.FOUR),
                Positions.of(Row.E, Column.FIVE),
                Positions.of(Row.F, Column.SIX),
                Positions.of(Row.G, Column.SEVEN)};

        Assertions.assertThat(actual).containsExactly(expected);
    }

    static Stream<Arguments> generateTopRightDownLeftPositions() {
        return Stream.of(
                Arguments.of("h1", "a8", MoveRule.DIAGONAL_TOP_LEFT),
                Arguments.of("a8", "h1",MoveRule.DIAGONAL_DOWN_RIGHT));
    }
}
