package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Path;
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

class PawnTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        Pawn blackPawn = Pawn.from(Side.BLACK);
        Pawn otherBlackPawn = Pawn.from(Side.BLACK);

        // when
        boolean result = blackPawn.equals(otherBlackPawn);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("providePositionsAndResultInInitialPosition")
    @DisplayName("초기 위치에 있는 Pawn은 한 칸 혹은 두 칸 전진할 수 있다.")
    void findPathInitialPosition(Side side, ChessPosition source, ChessPosition target, List<ChessPosition> expected) {
        // given
        Pawn pawn = Pawn.from(side);

        // when
        Path path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getChessPositions()).isEqualTo(expected);
    }

    private static Stream<Arguments> providePositionsAndResultInInitialPosition() {
        return Stream.of(
                Arguments.arguments(
                        Side.WHITE,
                        ChessPosition.of(File.B, Rank.TWO),
                        ChessPosition.of(File.B, Rank.THREE),
                        List.of(
                                ChessPosition.of(File.B, Rank.THREE)
                        )
                ),
                Arguments.arguments(
                        Side.WHITE,
                        ChessPosition.of(File.B, Rank.TWO),
                        ChessPosition.of(File.B, Rank.FOUR),
                        List.of(
                                ChessPosition.of(File.B, Rank.THREE),
                                ChessPosition.of(File.B, Rank.FOUR)
                        )
                ),
                Arguments.arguments(
                        Side.BLACK,
                        ChessPosition.of(File.B, Rank.SEVEN),
                        ChessPosition.of(File.B, Rank.FIVE),
                        List.of(
                                ChessPosition.of(File.B, Rank.SIX),
                                ChessPosition.of(File.B, Rank.FIVE)
                        )
                ),
                Arguments.arguments(
                        Side.BLACK,
                        ChessPosition.of(File.B, Rank.SEVEN),
                        ChessPosition.of(File.B, Rank.SIX),
                        List.of(
                                ChessPosition.of(File.B, Rank.SIX)
                        )
                )
        );
    }

    @Test
    @DisplayName("초기 위치가 아닌 Pawn은 한 칸만 전진할 수 있다.")
    void findPath() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.THREE);
        ChessPosition target = ChessPosition.of(File.C, Rank.FOUR);
        Pawn pawn = Pawn.from(Side.WHITE);

        // when
        Path path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getChessPositions()).isEqualTo(List.of(ChessPosition.of(File.C, Rank.FOUR)));
    }

    @Test
    @DisplayName("초기 위치가 아닌 Pawn은 두 칸 전진할 수 없다.")
    void findPathNotInInitialPosition() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.THREE);
        ChessPosition target = ChessPosition.of(File.C, Rank.FIVE);
        Pawn pawn = Pawn.from(Side.WHITE);

        // when
        Path path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Pawn의 대각선에 적 기물이 있다면 움직일 수 있다.")
    void findPathDiagonally() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.THREE);
        ChessPosition target = ChessPosition.of(File.D, Rank.FOUR);
        Pawn pawn = Pawn.from(Side.WHITE);
        Pawn targetPiece = Pawn.from(Side.BLACK);

        // when
        Path path = pawn.findPath(source, target, targetPiece);

        // then
        assertThat(path.getChessPositions()).isEqualTo(List.of(ChessPosition.of(File.D, Rank.FOUR)));
    }

    @Test
    @DisplayName("전진하고자 하는 타겟 위치에 기물이 존재하면 예외가 발생한다.")
    void findPathWithInvalidForwardTarget() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.C, Rank.THREE);
        Rook targetPiece = Rook.from(Side.BLACK);
        Pawn pawn = Pawn.from(Side.WHITE);

        // when & then
        assertThatThrownBy(() -> pawn.findPath(source, target, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Pawn 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.H, Rank.THREE);
        Pawn pawn = Pawn.from(Side.BLACK);

        // when
        Path path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.isEmpty()).isTrue();
    }
}
