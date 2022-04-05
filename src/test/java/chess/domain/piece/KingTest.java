package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("킹은 전후좌우, 대각선 방향으로 한 칸씩 움직인다.")
    void movableTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        King king = new King(team);
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        board.put(initialPosition, king);
        ChessBoard chessBoard = new ChessBoard(board);

        boolean result = king.isMovable(initialPosition, nextPosition, chessBoard);
        assertTrue(result);
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('e', 4), "킹이 앞으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('e', 2), "킹이 뒤로 한 칸 이동한다"),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('f', 3), "킹이 왼쪽으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('d', 3), "킹이 오른쪽으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('f', 2), "킹이 북동방향으로 한 칸 이동한다"),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('d', 2), "킹이 북서방향으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('f', 4), "킹이 남동방향으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('d', 2), "킹이 남서방향으로 한 칸 이동한다.")
        );
    }
}
