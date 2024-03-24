package chess.model.piece;

import static chess.model.Fixture.B2;
import static chess.model.Fixture.B3;
import static chess.model.Fixture.B4;
import static chess.model.Fixture.C2;
import static chess.model.Fixture.C3;
import static chess.model.Fixture.C4;
import static chess.model.Fixture.D3;
import static chess.model.Fixture.D4;
import static chess.model.Fixture.H3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.ChessPosition;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResultInInitialPosition")
    @DisplayName("초기 위치에 있는 Pawn이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPathInitialPosition(ChessPosition target, List<ChessPosition> expected) {
        // given
        Pawn pawn = new Pawn(Side.WHITE);

        // when
        List<ChessPosition> path = pawn.findPath(B2, target, new Empty());

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("초기 위치가 아닌 Pawn이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath() {
        // given
        Pawn pawn = new Pawn(Side.WHITE);

        // when
        List<ChessPosition> path = pawn.findPath(C3, C4, new Empty());

        // then
        assertThat(path).isEqualTo(List.of(C4));
    }

    @Test
    @DisplayName("Pawn의 대각선에 적 기물이 있다면 움직이는 경로를 찾는다.")
    void findPathCatchEnemy() {
        // given
        Pawn pawn = new Pawn(Side.WHITE);
        Pawn targetPiece = new Pawn(Side.BLACK);

        // when
        List<ChessPosition> path = pawn.findPath(C3, D4, targetPiece);

        // then
        assertThat(path).isEqualTo(List.of(D4));
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        Rook targetPiece = new Rook(Side.WHITE);
        Pawn pawn = new Pawn(Side.WHITE);

        //when //then
        assertThatThrownBy(() -> pawn.findPath(C2, D3, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Pawn 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Pawn pawn = new Pawn(Side.BLACK);

        // when
        List<ChessPosition> path = pawn.findPath(C2, H3, new Empty());

        // then
        assertThat(path).isEmpty();
    }

    private static Stream<Arguments> provideTargetPositionAndResultInInitialPosition() {
        return Stream.of(
                Arguments.arguments(B3, List.of(B3)),
                Arguments.arguments(B4, List.of(B3, B4))
        );
    }
}
