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

public class KingTest {

    private static Stream<Arguments> kingCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d3"), Position.of("d3")),
                Arguments.of(Position.of("d4"), Position.of("d5"), Position.of("d5")),
                Arguments.of(Position.of("d4"), Position.of("c4"), Position.of("c4")),
                Arguments.of(Position.of("d4"), Position.of("e4"), Position.of("e4")),
                Arguments.of(Position.of("d4"), Position.of("c3"), Position.of("c3")),
                Arguments.of(Position.of("d4"), Position.of("c5"), Position.of("c5")),
                Arguments.of(Position.of("d4"), Position.of("e3"), Position.of("e3")),
                Arguments.of(Position.of("d4"), Position.of("e5"), Position.of("e5")),
                Arguments.of(Position.of("d4"), Position.of("b3"), Position.of("d4"))
        );
    }

    @DisplayName("킹은 한 번에 수직 혹은 수평 혹은 대각선으로 한 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("kingCanMoveTestParameters")
    void kingMoveTest(Position currentPosition, Position newPosition, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        King king = new King(pieceInfo);
        King movedKing = king.move(newPosition, false, false, false);

        Position actualMovedPosition = movedKing.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }
}
