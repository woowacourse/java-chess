package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Knight가 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Knight knight = new Knight(Side.WHITE);

        // when
        List<ChessPosition> path = knight.findPath(source, target, null);

        // then
        assertThat(path).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        new ChessPosition(File.E, Rank.THREE),
                        List.of(new ChessPosition(File.E, Rank.THREE))
                ),
                Arguments.of(new ChessPosition(File.E, Rank.ONE),
                        List.of(new ChessPosition(File.E, Rank.ONE))
                ),
                Arguments.of(new ChessPosition(File.D, Rank.FOUR),
                        List.of(new ChessPosition(File.D, Rank.FOUR))
                ),
                Arguments.of(new ChessPosition(File.C, Rank.FIVE), List.of()),
                Arguments.of(new ChessPosition(File.A, Rank.FIVE), List.of())
        );
    }

}
