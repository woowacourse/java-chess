package chess.model.piece;

import static chess.model.Fixture.C2;
import static chess.model.Fixture.C3;
import static chess.model.Fixture.C5;
import static chess.model.Fixture.D2;
import static chess.model.Fixture.D3;
import static chess.model.Fixture.D6;
import static chess.model.Fixture.E4;
import static chess.model.Fixture.F2;
import static chess.model.Fixture.F6;
import static chess.model.Fixture.G3;
import static chess.model.Fixture.G5;
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

class KnightTest {
    @ParameterizedTest
    @MethodSource("provideTargetPositionAndResult")
    @DisplayName("Knight가 타켓 위치까지 움직이는 경로를 찾는다.")
    void findPath(ChessPosition target, List<ChessPosition> expected) {
        // given
        Knight knight = new Knight(Side.WHITE);

        // when
        List<ChessPosition> path = knight.findPath(E4, target, new Empty());

        // then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("타겟 위치에 아군 기물이 존재하면 예외가 발생한다.")
    void findPathWhenInvalidTarget() {
        // given
        Knight knight = new Knight(Side.WHITE);
        Pawn targetPiece = new Pawn(Side.WHITE);

        // when // then
        assertThatThrownBy(() -> knight.findPath(C2, D3, targetPiece))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Knight 움직임으로 타겟 위치에 도달할 수 없다면 빈 리스트를 반환한다.")
    void findPathWhenCanNotReachTargetPiece() {
        // given
        Knight knight = new Knight(Side.BLACK);

        // when
        List<ChessPosition> path = knight.findPath(C2, D2, new Empty());

        // then
        assertThat(path).isEmpty();
    }

    private static Stream<Arguments> provideTargetPositionAndResult() {
        return Stream.of(
                Arguments.of(C3, List.of(C3)),
                Arguments.of(C5, List.of(C5)),
                Arguments.of(D2, List.of(D2)),
                Arguments.of(D6, List.of(D6)),
                Arguments.of(F2, List.of(F2)),
                Arguments.of(F6, List.of(F6)),
                Arguments.of(G3, List.of(G3)),
                Arguments.of(G5, List.of(G5))
        );
    }
}
