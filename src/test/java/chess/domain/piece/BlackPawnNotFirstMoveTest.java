package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.BlackPawnNotFirstMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackPawnNotFirstMoveTest {

    private static Stream<Arguments> blackPawnMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d3"), true),
                Arguments.of(Position.of("d4"), Position.of("d2"), false),
                Arguments.of(Position.of("d4"), Position.of("d5"), false)
        );
    }

    @DisplayName("처음 움직이는 것이 아닌 검정 폰은 한 번에 한 칸 아래로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("blackPawnMoveTestParameters")
    void pawnMoveTest(Position currentPosition, Position newPosition, boolean expectedIsMoved) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.BLACK);
        Pawn blackPawnNotFirstMove = new Pawn(pieceInfo, new BlackPawnNotFirstMoveStrategy());
        Board board = new Board();

        boolean actualIsMoved = blackPawnNotFirstMove.move(newPosition, board, false);

        Assertions.assertThat(actualIsMoved).isEqualTo(expectedIsMoved);
    }
}
