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

class PawnTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResultInInitialPosition")
    @DisplayName("초기 위치에 있는 Pawn이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPathInitialPosition(ChessPosition target, List<ChessPosition> expected) {
        // given
        ChessPosition source = ChessPosition.of(File.B, Rank.TWO);
        Pawn pawn = Pawn.from(Side.WHITE);

        // when
        List<ChessPosition> path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("초기 위치가 아닌 Pawn이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.THREE);
        ChessPosition target = ChessPosition.of(File.C, Rank.FOUR);
        Pawn pawn = Pawn.from(Side.WHITE);

        // when
        List<ChessPosition> path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEqualTo(List.of(ChessPosition.of(File.C, Rank.FOUR)));
    }

    @Test
    @DisplayName("Pawn의 대각선에 적 기물이 있다면 움직이는 경로를 찾는다.")
    void findPathCatchEnemy() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.THREE);
        ChessPosition target = ChessPosition.of(File.D, Rank.FOUR);
        Pawn pawn = Pawn.from(Side.WHITE);
        Pawn targetPiece = Pawn.from(Side.BLACK);

        // when
        List<ChessPosition> path = pawn.findPath(source, target, targetPiece);

        // then
        assertThat(path).isEqualTo(List.of(ChessPosition.of(File.D, Rank.FOUR)));
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        ChessPosition source = ChessPosition.of(File.C, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.D, Rank.THREE);
        Rook targetPiece = Rook.from(Side.WHITE);
        Pawn pawn = Pawn.from(Side.WHITE);

        //when //then
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
        List<ChessPosition> path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path).isEmpty();
    }

    private static Stream<Arguments> provideTargetPositionAndResultInInitialPosition() {
        return Stream.of(
                Arguments.arguments(
                        ChessPosition.of(File.B, Rank.THREE),
                        List.of(
                                ChessPosition.of(File.B, Rank.THREE)
                        )
                ),
                Arguments.arguments(
                        ChessPosition.of(File.B, Rank.FOUR),
                        List.of(
                                ChessPosition.of(File.B, Rank.THREE),
                                ChessPosition.of(File.B, Rank.FOUR)
                        )
                )
        );
    }
}
