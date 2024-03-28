package chess.model.piece;

import chess.model.position.Position;
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
    void findPathInitialPosition(Side side, Position source, Position target, List<Position> expected) {
        // given
        Pawn pawn = Pawn.from(side);

        // when
        Path path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getPositions()).isEqualTo(expected);
    }

    private static Stream<Arguments> providePositionsAndResultInInitialPosition() {
        return Stream.of(
                Arguments.arguments(
                        Side.WHITE,
                        Position.of(File.B, Rank.TWO),
                        Position.of(File.B, Rank.THREE),
                        List.of(
                                Position.of(File.B, Rank.THREE)
                        )
                ),
                Arguments.arguments(
                        Side.WHITE,
                        Position.of(File.B, Rank.TWO),
                        Position.of(File.B, Rank.FOUR),
                        List.of(
                                Position.of(File.B, Rank.THREE),
                                Position.of(File.B, Rank.FOUR)
                        )
                ),
                Arguments.arguments(
                        Side.BLACK,
                        Position.of(File.B, Rank.SEVEN),
                        Position.of(File.B, Rank.FIVE),
                        List.of(
                                Position.of(File.B, Rank.SIX),
                                Position.of(File.B, Rank.FIVE)
                        )
                ),
                Arguments.arguments(
                        Side.BLACK,
                        Position.of(File.B, Rank.SEVEN),
                        Position.of(File.B, Rank.SIX),
                        List.of(
                                Position.of(File.B, Rank.SIX)
                        )
                )
        );
    }

    @Test
    @DisplayName("초기 위치가 아닌 Pawn은 한 칸만 전진할 수 있다.")
    void findPath() {
        // given
        Position source = Position.of(File.C, Rank.THREE);
        Position target = Position.of(File.C, Rank.FOUR);
        Pawn pawn = Pawn.from(Side.WHITE);

        // when
        Path path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.getPositions()).isEqualTo(List.of(Position.of(File.C, Rank.FOUR)));
    }

    @Test
    @DisplayName("초기 위치가 아닌 Pawn은 두 칸 전진할 수 없다.")
    void findPathNotInInitialPosition() {
        // given
        Position source = Position.of(File.C, Rank.THREE);
        Position target = Position.of(File.C, Rank.FIVE);
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
        Position source = Position.of(File.C, Rank.THREE);
        Position target = Position.of(File.D, Rank.FOUR);
        Pawn pawn = Pawn.from(Side.WHITE);
        Pawn targetPiece = Pawn.from(Side.BLACK);

        // when
        Path path = pawn.findPath(source, target, targetPiece);

        // then
        assertThat(path.getPositions()).isEqualTo(List.of(Position.of(File.D, Rank.FOUR)));
    }

    @Test
    @DisplayName("전진하고자 하는 타겟 위치에 기물이 존재하면 예외가 발생한다.")
    void findPathWithInvalidForwardTarget() {
        // given
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.THREE);
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
        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.H, Rank.THREE);
        Pawn pawn = Pawn.from(Side.BLACK);

        // when
        Path path = pawn.findPath(source, target, Blank.INSTANCE);

        // then
        assertThat(path.isEmpty()).isTrue();
    }
}
