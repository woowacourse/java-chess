package chess.model.piece;

import static chess.model.Fixture.A4;
import static chess.model.Fixture.B1;
import static chess.model.Fixture.B3;
import static chess.model.Fixture.C2;
import static chess.model.Fixture.D1;
import static chess.model.Fixture.D2;
import static chess.model.Fixture.D3;
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

class BishopTest {
    @ParameterizedTest
    @MethodSource("getPathsWhenBishopInC2")
    @DisplayName("Bishop이 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        Bishop bishop = new Bishop(Side.WHITE);

        // when
        List<ChessPosition> path = bishop.findPath(C2, target, new Empty());

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        Bishop bishop = new Bishop(Side.WHITE);
        Pawn targetPiece = new Pawn(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> bishop.findPath(C2, D3, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Bishop 움직임으로 타겟 위치에 도달할 수 없다면 예외를 발생한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Bishop bishop = new Bishop(Side.BLACK);

        // when //then
        assertThatThrownBy(() -> bishop.findPath(C2, D2, new Empty()))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Stream<Arguments> getPathsWhenBishopInC2() {
        return Stream.of(
                Arguments.of(D3, List.of(D3)),
                Arguments.of(D1, List.of(D1)),
                Arguments.of(B3, List.of(B3)),
                Arguments.of(B1, List.of(B1)),
                Arguments.of(A4, List.of(B3, A4)),
                Arguments.of(F5, List.of(D3, E4, F5))
        );
    }
}
