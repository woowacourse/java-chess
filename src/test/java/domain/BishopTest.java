package domain;

import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    private static Stream<Arguments> bishops() {
        return Stream.of(
                Arguments.arguments(File.B, Rank.SIX),
                Arguments.arguments(File.F, Rank.SIX),
                Arguments.arguments(File.B, Rank.TWO),
                Arguments.arguments(File.F, Rank.TWO)
        );
    }

    @DisplayName("비숍은 대각선 방향으로 임의의 칸 수만큼 움직인다.")
    @ParameterizedTest
    @MethodSource("bishops")
    void canMoveTest(File targetFile, Rank targetRank) {
        Bishop bishop = new Bishop(Side.BLACK);

        Position current = PositionFixture.d4();
        Position target = new Position(targetFile, targetRank);

        boolean actual = bishop.canMove(current, target);

        assertThat(actual).isTrue();
    }
}
