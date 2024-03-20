package chess.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Queen이 타켓 위치로 움직일 수 있는지 판단한다.")
    void canMove(ChessPosition target, boolean expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Queen queen = new Queen(Side.WHITE, source);

        // when
        boolean result = queen.canMove(target);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(new ChessPosition(File.C, Rank.THREE), true),
                Arguments.of(new ChessPosition(File.D, Rank.THREE), true),
                Arguments.of(new ChessPosition(File.C, Rank.EIGHT), true),
                Arguments.of(new ChessPosition(File.H, Rank.SEVEN), true),
                Arguments.of(new ChessPosition(File.F, Rank.ONE), false),
                Arguments.of(new ChessPosition(File.A, Rank.THREE), false)
        );
    }
}
