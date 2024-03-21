package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Bishop이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Bishop bishop = new Bishop(Side.WHITE);

        // when
        List<ChessPosition> path = bishop.findPath(source, target, null);

        // then
        assertThat(path).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        new ChessPosition(File.D, Rank.THREE),
                        List.of(
                                new ChessPosition(File.D, Rank.THREE)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.F, Rank.FIVE),
                        List.of(
                                new ChessPosition(File.D, Rank.THREE),
                                new ChessPosition(File.E, Rank.FOUR),
                                new ChessPosition(File.F, Rank.FIVE)
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
