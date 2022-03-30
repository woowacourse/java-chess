package chess.domain.game.state.position;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {
    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("validParameters")
    @DisplayName("세로로 이동이 가능해야 한다.")
    void moveToSameFile(Rank sourceRank, Rank targetRank, Direction direction, String testName) {
        Position target = Position.of(File.a, sourceRank.findNext(direction.getRank()));
        assertThat(target).isEqualTo(Position.of(File.a, targetRank));
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(Rank.Seven, Rank.Eight, Direction.Up, "위로 이동"),
            Arguments.of(Rank.Eight, Rank.Seven, Direction.Down, "아래로 이동")
        );
    }
}