package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Queen이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Queen queen = new Queen(Side.WHITE);

        // when
        List<ChessPosition> path = queen.findPath(source, target, null);

        // then
        assertThat(path).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        new ChessPosition(File.C, Rank.THREE),
                        List.of(
                                new ChessPosition(File.C, Rank.THREE)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.D, Rank.THREE),
                        List.of(
                                new ChessPosition(File.D, Rank.THREE)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.C, Rank.EIGHT),
                        List.of(
                                new ChessPosition(File.C, Rank.THREE),
                                new ChessPosition(File.C, Rank.FOUR),
                                new ChessPosition(File.C, Rank.FIVE),
                                new ChessPosition(File.C, Rank.SIX),
                                new ChessPosition(File.C, Rank.SEVEN),
                                new ChessPosition(File.C, Rank.EIGHT)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.H, Rank.SEVEN),
                        List.of(
                                new ChessPosition(File.D, Rank.THREE),
                                new ChessPosition(File.E, Rank.FOUR),
                                new ChessPosition(File.F, Rank.FIVE),
                                new ChessPosition(File.G, Rank.SIX),
                                new ChessPosition(File.H, Rank.SEVEN)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.F, Rank.ONE),
                        List.of()
                ),
                Arguments.of(
                        new ChessPosition(File.A, Rank.THREE),
                        List.of()
                )
        );
    }
}
