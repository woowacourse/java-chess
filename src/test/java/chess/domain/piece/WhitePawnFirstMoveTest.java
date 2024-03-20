package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.WhitePawnFirstMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WhitePawnFirstMoveTest {

    private static Stream<Arguments> whitePawnMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d5"), true),
                Arguments.of(Position.of("d4"), Position.of("d6"), true),
                Arguments.of(Position.of("d4"), Position.of("d7"), false),
                Arguments.of(Position.of("d4"), Position.of("d3"), false)
        );
    }

    @DisplayName("처음 움직이는 흰색 폰은 1칸 혹은 2칸 위로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("whitePawnMoveTestParameters")
    void pawnMoveTest(Position currentPosition, Position newPosition, boolean expectedIsMoved) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Pawn whitePawnFirstMove = new Pawn(pieceInfo, new WhitePawnFirstMoveStrategy());
        Board board = new Board();

        boolean actualIsMoved = whitePawnFirstMove.move(newPosition, board);

        Assertions.assertThat(actualIsMoved).isEqualTo(expectedIsMoved);
    }
}
