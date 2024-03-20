package domain;

import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(File.C, Rank.FIVE),
                Arguments.arguments(File.E, Rank.FIVE),
                Arguments.arguments(File.B, Rank.SIX),
                Arguments.arguments(File.F, Rank.SIX)
        );
    }

    private static Stream<Arguments> immovableTargetPosition() {
        return Stream.of(
                Arguments.arguments(File.C, Rank.FOUR),
                Arguments.arguments(File.D, Rank.FIVE)
        );
    }

    @DisplayName("비숍은 대각선 방향으로 임의의 칸 수만큼 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargetPosition")
    void canMoveTest(File targetFile, Rank targetRank) {
        Bishop bishop = new Bishop(Side.BLACK);

        Position current = PositionFixture.d4();
        Position target = new Position(targetFile, targetRank);

        boolean actual = bishop.canMove(current, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("비숍은 수직 또는 수평 방향으로 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargetPosition")
    void cantMoveTest(File targetFile, Rank targetRank) {
        Bishop bishop = new Bishop(Side.BLACK);

        Position current = PositionFixture.d4();
        Position target = new Position(targetFile, targetRank);

        boolean actual = bishop.canMove(current, target);

        assertThat(actual).isFalse();
    }
}
