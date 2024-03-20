package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    private static Stream<Arguments> rooks() {
        return Stream.of(
                Arguments.arguments(Rank.B, File.FIFTH),
                Arguments.arguments(Rank.F, File.FIFTH),
                Arguments.arguments(Rank.D, File.SECOND),
                Arguments.arguments(Rank.D, File.SECOND)
        );
    }

    @DisplayName("룩은 수직 또는 수평 방향으로 임의의 칸 수만큼 움직인다.")
    @ParameterizedTest
    @MethodSource("rooks")
    void canMoveTest(Rank targetRank, File targetFile) {
        Rook rook = new Rook(Side.BLACK);

        Position current = new Position(Rank.D, File.FIFTH);
        Position target = new Position(targetRank, targetFile);

        boolean actual = rook.canMove(current, target);

        assertThat(actual).isTrue();
    }
}
