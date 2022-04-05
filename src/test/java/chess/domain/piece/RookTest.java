package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {
    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("룩은 상하좌우 방향으로 칸 수의 제한 없이 움직인다. ")
    void movableTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        Rook rook = new Rook(team);
        board.put(initialPosition, rook);
        ChessBoard chessBoard = new ChessBoard(board);
        boolean result = rook.isMovable(initialPosition, nextPosition, chessBoard);
        assertTrue(result);
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('e', 8), "흑팀의 룩이 끝까지 전진한다.."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('e', 1), "흑팀의 룩이 끝까지 후진한다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('h', 3), "흑팀의 룩이 끝까지 왼쪽으로 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('a', 3), "흑팀의 룩이 끝까지 오른쪽으로 간다.")
        );
    }

    @Test
    @DisplayName("룩이 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest() {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        ChessPiece rook = new Rook(Team.BLACK);
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("e3");
        board.put(sourcePosition, rook);
        board.put(ChessBoardPosition.from("e5"), new Knight(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(board);
        ChessBoardPosition targetPosition = ChessBoardPosition.from("e7");

        boolean result = rook.isMovable(sourcePosition, targetPosition, chessBoard);
        assertFalse(result);
    }

    @Test
    @DisplayName("룩이 타겟위치로 갈 수 있으면 true를 반환")
    void movableTest2() {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        ChessPiece rook = new Rook(Team.WHITE);
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("e3");
        board.put(sourcePosition, rook);
        ChessBoard chessBoard = new ChessBoard(board);
        ChessBoardPosition targetPosition = ChessBoardPosition.from("e5");

        boolean result = rook.isMovable(sourcePosition, targetPosition, chessBoard);
        assertTrue(result);
    }
}
