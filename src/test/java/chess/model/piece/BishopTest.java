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

class BishopTest {

    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Bishop이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        Bishop bishop = Bishop.from(Side.WHITE);

        // when
        List<ChessPosition> path = bishop.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.D, Rank.THREE);
        Bishop bishop = Bishop.from(Side.WHITE);
        Pawn targetPiece = Pawn.from(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> bishop.findPath(source, target, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Bishop 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.D, Rank.TWO);
        Bishop bishop = Bishop.from(Side.BLACK);

        // when
        List<ChessPosition> path = bishop.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEmpty();
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(
                        ChessPosition.of(File.D, Rank.THREE),
                        List.of(
                                ChessPosition.of(File.D, Rank.THREE)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.D, Rank.ONE),
                        List.of(
                                ChessPosition.of(File.D, Rank.ONE)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.B, Rank.THREE),
                        List.of(
                                ChessPosition.of(File.B, Rank.THREE)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.B, Rank.ONE),
                        List.of(
                                ChessPosition.of(File.B, Rank.ONE)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.A, Rank.FOUR),
                        List.of(
                                ChessPosition.of(File.B, Rank.THREE),
                                ChessPosition.of(File.A, Rank.FOUR)
                        )
                ),
                Arguments.of(
                        ChessPosition.of(File.F, Rank.FIVE),
                        List.of(
                                ChessPosition.of(File.D, Rank.THREE),
                                ChessPosition.of(File.E, Rank.FOUR),
                                ChessPosition.of(File.F, Rank.FIVE)
                        )
                )
        );
    }
}
