package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Bishop이 타켓 위치로 움직일 수 있는지 판단한다.")
    void canMove(ChessPosition target, boolean expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Bishop bishop = new Bishop(Side.WHITE);

        // when
        boolean result = bishop.canMove(source, target);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(new ChessPosition(File.D, Rank.THREE), true),
                Arguments.of(new ChessPosition(File.F, Rank.FIVE), true),
                Arguments.of(new ChessPosition(File.F, Rank.ONE), false),
                Arguments.of(new ChessPosition(File.A, Rank.THREE), false)
        );
    }
}
