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
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        Bishop bishop = new Bishop(Side.WHITE);

        // when
        List<ChessPosition> path = bishop.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        ChessPosition target = new ChessPosition(File.D, Rank.THREE);
        Bishop bishop = new Bishop(Side.WHITE);
        Pawn targetPiece = new Pawn(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> bishop.findPath(source, target, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Bishop 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        ChessPosition source = new ChessPosition(File.C, Rank.TWO);
        ChessPosition target = new ChessPosition(File.D, Rank.TWO);
        Bishop bishop = new Bishop(Side.BLACK);

        // when
        List<ChessPosition> path = bishop.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEmpty();
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
                        new ChessPosition(File.D, Rank.ONE),
                        List.of(
                                new ChessPosition(File.D, Rank.ONE)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.B, Rank.THREE),
                        List.of(
                                new ChessPosition(File.B, Rank.THREE)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.B, Rank.ONE),
                        List.of(
                                new ChessPosition(File.B, Rank.ONE)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.A, Rank.FOUR),
                        List.of(
                                new ChessPosition(File.B, Rank.THREE),
                                new ChessPosition(File.A, Rank.FOUR)
                        )
                ),
                Arguments.of(
                        new ChessPosition(File.F, Rank.FIVE),
                        List.of(
                                new ChessPosition(File.D, Rank.THREE),
                                new ChessPosition(File.E, Rank.FOUR),
                                new ChessPosition(File.F, Rank.FIVE)
                        )
                )
        );
    }
}
