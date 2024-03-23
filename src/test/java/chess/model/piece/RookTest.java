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

class RookTest {

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Rook은 상하좌우로 원하는 만큼 움직일 수 있다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        Rook rook = Rook.from(Side.WHITE);

        // when
        List<ChessPosition> path = rook.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        ChessPosition.of(File.C, Rank.THREE),
                        List.of(
                                ChessPosition.of(File.C, Rank.THREE)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.C, Rank.ONE),
                        List.of(
                                ChessPosition.of(File.C, Rank.ONE)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.B, Rank.TWO),
                        List.of(
                                ChessPosition.of(File.B, Rank.TWO)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.D, Rank.TWO),
                        List.of(
                                ChessPosition.of(File.D, Rank.TWO)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.A, Rank.TWO),
                        List.of(
                                ChessPosition.of(File.B, Rank.TWO),
                                ChessPosition.of(File.A, Rank.TWO)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.C, Rank.EIGHT),
                        List.of(
                                ChessPosition.of(File.C, Rank.THREE),
                                ChessPosition.of(File.C, Rank.FOUR),
                                ChessPosition.of(File.C, Rank.FIVE),
                                ChessPosition.of(File.C, Rank.SIX),
                                ChessPosition.of(File.C, Rank.SEVEN),
                                ChessPosition.of(File.C, Rank.EIGHT)
                        )
                )
        );
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.D, Rank.THREE);
        Rook rook = Rook.from(Side.WHITE);
        Pawn targetPiece = Pawn.from(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> rook.findPath(source, target, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Rook 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.H, Rank.THREE);
        Rook rook = Rook.from(Side.BLACK);

        // when
        List<ChessPosition> path = rook.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEmpty();
    }
}
