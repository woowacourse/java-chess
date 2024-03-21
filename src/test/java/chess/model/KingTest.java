package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("King이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        King king = new King(Side.WHITE);

        // when
        List<ChessPosition> path = king.findPath(source, target, null);

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        ChessPosition target = new ChessPosition(File.D, Rank.THREE);
        King king = new King(Side.WHITE);
        Pawn targetPiece = new Pawn(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> king.findPath(source, target, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("King 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        ChessPosition target = new ChessPosition(File.H, Rank.THREE);
        King king = new King(Side.BLACK);

        // when
        List<ChessPosition> path = king.findPath(source, target, null);

        // then
        assertThat(path).isEmpty();
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        new ChessPosition(File.B, Rank.ONE),
                        List.of(new ChessPosition(File.B, Rank.ONE))
                ),
                Arguments.of(
                        new ChessPosition(File.C, Rank.ONE),
                        List.of(new ChessPosition(File.C, Rank.ONE))
                ),
                Arguments.of(
                        new ChessPosition(File.D, Rank.ONE),
                        List.of(new ChessPosition(File.D, Rank.ONE))
                ),
                Arguments.of(
                        new ChessPosition(File.B, Rank.TWO),
                        List.of(new ChessPosition(File.B, Rank.TWO))
                ),
                Arguments.of(
                        new ChessPosition(File.D, Rank.TWO),
                        List.of(new ChessPosition(File.D, Rank.TWO))
                ),
                Arguments.of(
                        new ChessPosition(File.B, Rank.THREE),
                        List.of(new ChessPosition(File.B, Rank.THREE))
                ),
                Arguments.of(
                        new ChessPosition(File.C, Rank.THREE),
                        List.of(new ChessPosition(File.C, Rank.THREE))
                ),
                Arguments.of(
                        new ChessPosition(File.D, Rank.THREE),
                        List.of(new ChessPosition(File.D, Rank.THREE))
                )
        );
    }
}
