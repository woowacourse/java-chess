package chess.domains.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @DisplayName("수직, 수평의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e4, e7, e5, e6", "e4, h4, f4, g4", "e4, e1, e3, e2", "e4, b4, d4, c4"})
    void findRoute_1(String sourceName, String targetName, String route1, String route2) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);

        List<Position> expected = new ArrayList<>(Arrays.asList(
                Position.ofPositionName(route1), Position.ofPositionName(route2)));

        assertThat(source.findRoute(target)).isEqualTo(expected);
    }

    @DisplayName("대각선의 경로를 반환할 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e4, h7, f5, g6", "e4, b1, d3, c2", "e4, b7, d5, c6", "e4, h1, f3, g2"})
    void findRoute_2(String sourceName, String targetName, String route1, String route2) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);

        List<Position> expected = new ArrayList<>(Arrays.asList(
                Position.ofPositionName(route1), Position.ofPositionName(route2)));

        assertThat(source.findRoute(target)).isEqualTo(expected);
    }
}
