package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Knight가 타켓 위치로 움직일 수 있는지 판단한다.")
    void canMove(ChessPosition target, boolean expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Knight knight = new Knight(Side.WHITE);

        // when
        boolean result = knight.canMove(source, target);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(new ChessPosition(File.E, Rank.THREE), true),
                Arguments.of(new ChessPosition(File.E, Rank.ONE), true),
                Arguments.of(new ChessPosition(File.D, Rank.FOUR), true),
                Arguments.of(new ChessPosition(File.C, Rank.FIVE), false),
                Arguments.of(new ChessPosition(File.A, Rank.FIVE), false)
        );
    }

}
