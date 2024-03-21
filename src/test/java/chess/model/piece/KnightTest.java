package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Knight가 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = new ChessPosition(File.E, Rank.FOUR);
        Knight knight = new Knight(Side.WHITE);

        // when
        List<ChessPosition> path = knight.findPath(source, target, null);

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        ChessPosition target = new ChessPosition(File.D, Rank.THREE);
        Knight knight = new Knight(Side.WHITE);
        Pawn targetPiece = new Pawn(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> knight.findPath(source, target, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Knight 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        ChessPosition target = new ChessPosition(File.D, Rank.TWO);
        Knight knight = new Knight(Side.BLACK);

        // when
        List<ChessPosition> path = knight.findPath(source, target, null);

        // then
        assertThat(path).isEmpty();
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        new ChessPosition(File.C, Rank.THREE),
                        List.of(new ChessPosition(File.C, Rank.THREE))
                ),
                Arguments.of(
                        new ChessPosition(File.C, Rank.FIVE),
                        List.of(new ChessPosition(File.C, Rank.FIVE))
                ),
                Arguments.of(
                        new ChessPosition(File.D, Rank.TWO),
                        List.of(new ChessPosition(File.D, Rank.TWO))
                ),
                Arguments.of(
                        new ChessPosition(File.D, Rank.SIX),
                        List.of(new ChessPosition(File.D, Rank.SIX))
                ),
                Arguments.of(
                        new ChessPosition(File.F, Rank.TWO),
                        List.of(new ChessPosition(File.F, Rank.TWO))
                ),
                Arguments.of(
                        new ChessPosition(File.F, Rank.SIX),
                        List.of(new ChessPosition(File.F, Rank.SIX))
                ),
                Arguments.of(
                        new ChessPosition(File.G, Rank.THREE),
                        List.of(new ChessPosition(File.G, Rank.THREE))
                ),
                Arguments.of(
                        new ChessPosition(File.G, Rank.FIVE),
                        List.of(new ChessPosition(File.G, Rank.FIVE))
                )
        );
    }

}
