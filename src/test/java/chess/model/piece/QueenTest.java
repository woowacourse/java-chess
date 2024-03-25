package chess.model.piece;

import static chess.model.Fixture.A2;
import static chess.model.Fixture.A4;
import static chess.model.Fixture.B1;
import static chess.model.Fixture.B2;
import static chess.model.Fixture.B3;
import static chess.model.Fixture.C1;
import static chess.model.Fixture.C2;
import static chess.model.Fixture.C3;
import static chess.model.Fixture.C4;
import static chess.model.Fixture.C5;
import static chess.model.Fixture.C6;
import static chess.model.Fixture.C7;
import static chess.model.Fixture.C8;
import static chess.model.Fixture.D1;
import static chess.model.Fixture.D2;
import static chess.model.Fixture.D3;
import static chess.model.Fixture.D4;
import static chess.model.Fixture.E4;
import static chess.model.Fixture.F5;
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

class QueenTest {
    @ParameterizedTest
    @MethodSource("getPathsWhenQueenInC2")
    @DisplayName("Queen이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        Queen queen = new Queen(Side.WHITE);

        // when
        List<ChessPosition> path = queen.findPath(C2, target, new Empty());

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        Queen queen = new Queen(Side.WHITE);
        Pawn targetPiece = new Pawn(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> queen.findPath(C2, D3, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Queen 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Queen queen = new Queen(Side.BLACK);

        // when

        // then
        assertThatThrownBy(() -> queen.findPath(C2, D4, new Empty()))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Stream<Arguments> getPathsWhenQueenInC2() {
        return Stream.of(
                // 대각선 움직임
                Arguments.of(D3, List.of(D3)),
                Arguments.of(D1, List.of(D1)),
                Arguments.of(B3, List.of(B3)),
                Arguments.of(B1, List.of(B1)),
                Arguments.of(A4, List.of(B3, A4)),
                Arguments.of(F5, List.of(D3, E4, F5)),
                // 상하좌우 움직임
                Arguments.of(C3, List.of(C3)),
                Arguments.of(C1, List.of(C1)),
                Arguments.of(B2, List.of(B2)),
                Arguments.of(D2, List.of(D2)),
                Arguments.of(A2, List.of(B2, A2)),
                Arguments.of(C8, List.of(C3, C4, C5, C6, C7, C8))
        );
    }
}
