package chess.domain.piece;

import chess.domain.BoardFixtures;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    private static Position whitePawnPositionAtStartingPoint = new Position("b2");

    private static Stream<Arguments> generatePossiblePositionsAtStartingPoint() {
        return Stream.of("b3", "b4")
                .map(Arguments::of);
    }

    @DisplayName("white pawn은 시작점인 경우 top 방향으로 한 칸 혹은 두 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("generatePossiblePositionsAtStartingPoint")
    void whitePawn_시작점_top방향_한칸_두칸_이동_가능하다(Position targetPosition) {
        List<List<Piece>> board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Pawn pawn = new Pawn(Color.WHITE);

        Assertions.assertDoesNotThrow(() -> pawn.validateMove(board, whitePawnPositionAtStartingPoint, targetPosition));
    }
}