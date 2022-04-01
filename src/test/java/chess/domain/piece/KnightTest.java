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

class KnightTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("나이트는 상하좌우로 두 칸 전진한 상태에서 좌우로 한 칸 움직인다.")
    void movableTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Knight knight = new Knight(team);
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        board.put(initialPosition, knight);
        ChessBoard chessBoard = new ChessBoard(board);
        boolean result = knight.isMovable(initialPosition, nextPosition, chessBoard);
        assertTrue(result);
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('f', 5), "흑팀의 나이트는 두 칸 전진하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('d', 5), "흑팀의 나이트는 두 칸 전진하고 우로 한칸 움직인다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('d', 1), "흑팀의 나이트는 두 칸 후진하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('f', 1), "흑팀의 나이트는 두 칸 후진하고 우로 한칸 움직인다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('g', 2), "흑팀의 나이트는 두 칸 왼쪽으로 이동하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('g', 4), "흑팀의 나이트는 두 칸 왼쪽으로 이동하고 우로 한칸 움직인다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('c', 4), "흑팀의 나이트는 두 칸 오른쪽으로 이동하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('c', 2), "흑팀의 나이트는 두 칸 오른쪽으로 이동하고 우로 한칸 움직인다.")
        );
    }
}
