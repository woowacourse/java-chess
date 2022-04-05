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

class QueenTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("퀸은 전후좌우, 대각선으로 칸수 제한 없이 움직인다.")
    void movableTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        Queen queen = new Queen(team);
        board.put(initialPosition, queen);
        ChessBoard chessBoard = new ChessBoard(board);
        boolean result = queen.isMovable(initialPosition, nextPosition, chessBoard);
        assertTrue(result);
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('g', 1), "퀸은 1사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('c', 1), "퀸은 2사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('a', 7), "퀸은 3사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('h', 6), "퀸은 4사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('e', 8), "퀸이 끝까지 전진한다.."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('e', 1), "퀸이 끝까지 후진한다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('h', 3), "퀸이 끝까지 왼쪽으로 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('a', 3), "퀸이 끝까지 오른쪽으로 간다.")
        );
    }

    @Test
    @DisplayName("퀸이 대각선 방향의 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest() {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("e3");
        board.put(sourcePosition, queen);
        board.put(ChessBoardPosition.from("d4"), new Knight(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(board);
        ChessBoardPosition targetPosition = ChessBoardPosition.from("c5");

        boolean result = queen.isMovable(sourcePosition, targetPosition, chessBoard);
        assertFalse(result);
    }

    @Test
    @DisplayName("퀸이 상하좌우 방향의 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest2() {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("e3");
        board.put(sourcePosition, queen);
        board.put(ChessBoardPosition.from("e4"), new Knight(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(board);
        ChessBoardPosition targetPosition = ChessBoardPosition.from("e5");

        boolean result = queen.isMovable(sourcePosition, targetPosition, chessBoard);
        assertFalse(result);
    }
}
