package domain;

import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(File.C, Rank.FOUR),
                Arguments.arguments(File.D, Rank.FIVE),
                Arguments.arguments(File.C, Rank.FIVE),
                Arguments.arguments(File.E, Rank.FIVE),
                Arguments.arguments(File.B, Rank.FOUR),
                Arguments.arguments(File.D, Rank.SIX),
                Arguments.arguments(File.B, Rank.SIX),
                Arguments.arguments(File.F, Rank.SIX)
        );
    }

    @DisplayName("퀸은 수직, 수평 또는 대각선 방향으로 임의의 칸 수만큼 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargetPosition")
    void canMoveTest(File targetFile, Rank targetRank) {
        Queen queen = new Queen(Side.BLACK);

        Position current = PositionFixture.D4;
        Position target = new Position(targetFile, targetRank);

//        boolean actual = queen.isRuleBroken(current, target, new LinkedHashMap<>());
//
//        assertThat(actual).isTrue();
    }
}
