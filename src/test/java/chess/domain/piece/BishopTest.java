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

class BishopTest {
    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("비숍은 대각선 방향으로 칸 수의 제한 없이 움직인다. ")
    void moveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        Bishop bishop = new Bishop(team);
        board.put(initialPosition, bishop);
        ChessBoard chessBoard = new ChessBoard(board);
        boolean result = bishop.isMovable(initialPosition, nextPosition, chessBoard);
        assertTrue(result);
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('g', 1), "흑팀의 비숍은 1사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('c', 1), "흑팀의 비숍은 2사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('a', 7), "흑팀의 비숍은 3사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('e', 3),
                        ChessBoardPosition.of('h', 6), "흑팀의 비숍은 4사분면으로 끝까지 간다.")
        );
    }

    @Test
    @DisplayName("비숍이 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest() {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        Bishop bishop = new Bishop(Team.BLACK);
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("e3");
        board.put(sourcePosition, bishop);
        board.put(ChessBoardPosition.from("d4"), new Knight(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(board);
        ChessBoardPosition targetPosition = ChessBoardPosition.from("c5");

        boolean result = bishop.isMovable(sourcePosition, targetPosition, chessBoard);
        assertFalse(result);
    }

    @Test
    @DisplayName("비숍이 타겟위치로 갈 수 있으면 true를 반환")
    void movableTest2() {
        Map<ChessBoardPosition, ChessPiece> board =new HashMap<>();
        Bishop bishop = new Bishop(Team.WHITE);
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("e3");
        board.put(sourcePosition, bishop);
        ChessBoard chessBoard = new ChessBoard(board);
        ChessBoardPosition targetPosition = ChessBoardPosition.from("c5");

        boolean result = bishop.isMovable(sourcePosition, targetPosition, chessBoard);
        assertTrue(result);
    }
}
