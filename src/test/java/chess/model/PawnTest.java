package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResultInInitialPosition")
    @DisplayName("초기 위치에 있는 Pawn이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPathInitialPosition(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Pawn pawn = new Pawn(Side.WHITE);

        // when
        List<ChessPosition> path = pawn.findPath(source, target, null);

        // then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Pawn이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.THREE);
        Pawn pawn = new Pawn(Side.WHITE);

        // when
        List<ChessPosition> path = pawn.findPath(source, target, null);

        // then
        assertThat(path).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResultInInitialPosition() {
        return Stream.of(
                Arguments.of(
                        new ChessPosition(File.C, Rank.THREE),
                        List.of(new ChessPosition(File.C, Rank.THREE))
                ),
                Arguments.of(
                        new ChessPosition(File.C, Rank.FOUR),
                        List.of(
                                new ChessPosition(File.C, Rank.THREE),
                                new ChessPosition(File.C, Rank.FOUR)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.C, Rank.FIVE),
                        List.of()
                ),
                Arguments.of(
                        new ChessPosition(File.A, Rank.THREE),
                        List.of()
                )
        );
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        new ChessPosition(File.C, Rank.FOUR),
                        List.of(new ChessPosition(File.C, Rank.FOUR))
                ),
                Arguments.of(
                        new ChessPosition(File.C, Rank.FIVE),
                        List.of()
                ),
                Arguments.of(
                        new ChessPosition(File.A, Rank.THREE),
                        List.of()
                )
        );
    }
}
