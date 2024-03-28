package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BishopTest {

    private static Stream<Arguments> bishopMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("a1"), Position.of("a1")),
                Arguments.of(Position.of("d4"), Position.of("b6"), Position.of("b6")),
                Arguments.of(Position.of("d4"), Position.of("e5"), Position.of("e5")),
                Arguments.of(Position.of("d4"), Position.of("g1"), Position.of("g1")),
                Arguments.of(Position.of("d4"), Position.of("b3"), Position.of("d4"))
        );
    }

    @DisplayName("비숍은 한 번에 대각선으로 여러 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("bishopMoveTestParameters")
    void bishopMoveTest(Position currentPosition, Position newPosition, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Bishop bishop = new Bishop(pieceInfo);
        Bishop movedBishop = bishop.move(newPosition, false, false, false);

        Position actualMovedPosition = movedBishop.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }
}
