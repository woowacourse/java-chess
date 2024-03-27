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

public class KnightTest {

    private static Stream<Arguments> knightMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("f5"), Position.of("f5")),
                Arguments.of(Position.of("d4"), Position.of("b5"), Position.of("b5")),
                Arguments.of(Position.of("d4"), Position.of("f3"), Position.of("f3")),
                Arguments.of(Position.of("d4"), Position.of("b3"), Position.of("b3")),
                Arguments.of(Position.of("d4"), Position.of("e6"), Position.of("e6")),
                Arguments.of(Position.of("d4"), Position.of("c6"), Position.of("c6")),
                Arguments.of(Position.of("d4"), Position.of("e2"), Position.of("e2")),
                Arguments.of(Position.of("d4"), Position.of("c2"), Position.of("c2")),
                Arguments.of(Position.of("d4"), Position.of("d5"), Position.of("d4"))
        );
    }

    @DisplayName("나이트는 한 번에 수직으로 2칸과 수평으로 1칸 혹은 수평으로 2칸과 수직으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("knightMoveTestParameters")
    void knightMoveTest(Position currentPosition, Position newPosition, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Knight knight = new Knight(pieceInfo);
        Knight movedKnight = knight.move(newPosition, false, false, false);

        Position actualMovedPosition = movedKnight.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }
}
