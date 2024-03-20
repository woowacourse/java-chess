package domain;

import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(File.C, Rank.FOUR),
                Arguments.arguments(File.D, Rank.FIVE),
                Arguments.arguments(File.C, Rank.FIVE),
                Arguments.arguments(File.E, Rank.FIVE)
        );
    }

    private static Stream<Arguments> immovableTargetPosition() {
        return Stream.of(
                Arguments.arguments(File.B, Rank.FOUR),
                Arguments.arguments(File.D, Rank.SIX),
                Arguments.arguments(File.B, Rank.SIX),
                Arguments.arguments(File.F, Rank.SIX)
        );
    }

    @DisplayName("킹은 수직, 수평 또는 대각선 방향으로 한 칸 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargetPosition")
    void canMoveTest(File targetFile, Rank targetRank) {
        King king = new King(Side.BLACK);

        Position current = PositionFixture.d4();
        Position target = new Position(targetFile, targetRank);

        boolean actual = king.canMove(current, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("킹은 수직, 수평 또는 대각선 방향으로 두 칸 이상 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargetPosition")
    void cantMoveTest(File targetFile, Rank targetRank) {
        King king = new King(Side.BLACK);

        Position current = PositionFixture.d4();
        Position target = new Position(targetFile, targetRank);

        boolean actual = king.canMove(current, target);

        assertThat(actual).isFalse();
    }
}
