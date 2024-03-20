package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResultInInitialPosition")
    @DisplayName("초기 위치에 있는 Pawn이 타켓 위치로 움직일 수 있는지 판단한다.")
    void canMoveInitialPosition(ChessPosition target, boolean expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Pawn pawn = new Pawn(Side.WHITE);

        // when
        boolean result = pawn.canMove(source, target);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Pawn이 타켓 위치로 움직일 수 있는지 판단한다.")
    void canMove(ChessPosition target, boolean expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.THREE);
        Pawn pawn = new Pawn(Side.WHITE);

        // when
        boolean result = pawn.canMove(source, target);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResultInInitialPosition() {
        return Stream.of(
                Arguments.of(new ChessPosition(File.C, Rank.THREE), true),
                Arguments.of(new ChessPosition(File.C, Rank.FOUR), true),
                Arguments.of(new ChessPosition(File.C, Rank.FIVE), false),
                Arguments.of(new ChessPosition(File.A, Rank.THREE), false)
        );
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(new ChessPosition(File.C, Rank.FOUR), true),
                Arguments.of(new ChessPosition(File.C, Rank.FIVE), false),
                Arguments.of(new ChessPosition(File.A, Rank.THREE), false)
        );
    }
}
