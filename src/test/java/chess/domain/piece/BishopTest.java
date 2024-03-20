package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.BishopMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BishopTest {

    private static Stream<Arguments> bishopMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("a1"), true),
                Arguments.of(Position.of("d4"), Position.of("b6"), true),
                Arguments.of(Position.of("d4"), Position.of("e5"), true),
                Arguments.of(Position.of("d4"), Position.of("g1"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("비숍은 한 번에 대각선으로 여러 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("bishopMoveTestParameters")
    void bishopMoveTest(Position currentPosition, Position newPosition, boolean expectedIsMoved) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Bishop bishop = new Bishop(pieceInfo, new BishopMoveStrategy());
        Board board = new Board();

        boolean actualIsMoved = bishop.move(newPosition, board, false);

        Assertions.assertThat(actualIsMoved).isEqualTo(expectedIsMoved);
    }
}
