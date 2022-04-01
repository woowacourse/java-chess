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

class PawnTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("폰은 한 칸 또는 두 칸 전진한다.")
    void movableTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Pawn pawn = new Pawn(team);
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.put(initialPosition, pawn);
        ChessBoard chessBoard = new ChessBoard(board);
        boolean result = pawn.isMovable(initialPosition, nextPosition, chessBoard);
        assertTrue(result);
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, ChessBoardPosition.of('a', 7),
                        ChessBoardPosition.of('a', 6), "흑팀의 폰은 초기상태에서 한칸 전진한다"),
                Arguments.of(Team.WHITE, ChessBoardPosition.of('a', 2),
                        ChessBoardPosition.of('a', 3), "백팀의 폰은 초기상태에서 한칸 전진한다"),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('a', 7),
                        ChessBoardPosition.of('a', 5), "흑팀의 폰은 초기상태에서 두칸 전진한다"),
                Arguments.of(Team.WHITE, ChessBoardPosition.of('a', 2),
                        ChessBoardPosition.of('a', 4), "백팀의 폰은 초기상태에서 두칸 전진한다"),
                Arguments.of(Team.WHITE, ChessBoardPosition.of('a', 5),
                        ChessBoardPosition.of('a', 6), "백팀의 폰은 초기상태 이후 한칸 전진한다")
        );
    }

    @Test
    @DisplayName("앞으로 3칸 전진할 수 없다")
    void moveTest3() {
        ChessBoardPosition initialPosition = ChessBoardPosition.of('a', 2);
        Pawn pawn = new Pawn(Team.BLACK);
        ChessBoardPosition nextPosition = ChessBoardPosition.of('a', 5);
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.put(initialPosition, pawn);
        ChessBoard chessBoard = new ChessBoard(board);
        boolean result = pawn.isMovable(initialPosition, nextPosition, chessBoard);
        assertFalse(result);
    }


    @Test
    @DisplayName("초기상태 이후로는 두 칸 이상 전진할 수 없다.")
    void notMoveTest() {
        ChessBoardPosition initialPosition = ChessBoardPosition.of('a', 3);
        Pawn pawn = new Pawn(Team.BLACK);
        ChessBoardPosition nextPosition = ChessBoardPosition.of('a', 5);
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.put(initialPosition, pawn);
        ChessBoard chessBoard = new ChessBoard(board);
        boolean result = pawn.isMovable(initialPosition, nextPosition, chessBoard);
        assertFalse(result);
    }

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("diagonalParameters")
    @DisplayName("폰은 적이 대각선 방향에 존재하는 경우 이동 가능하다.")
    void diagonalMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Pawn pawn = new Pawn(team);
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.put(initialPosition, pawn);
        board.putAll(getWhiteChessPieces());
        board.putAll(getBlackChessPieces());
        assertTrue(pawn.isMovable(initialPosition, nextPosition, new ChessBoard(board)));
    }

    private Map<ChessBoardPosition, ChessPiece> getWhiteChessPieces() {
        Map<ChessBoardPosition, ChessPiece> whiteChessPieces = new HashMap<>();
        whiteChessPieces.put(ChessBoardPosition.from("a6"), new Pawn(Team.WHITE));
        whiteChessPieces.put(ChessBoardPosition.from("c6"), new Pawn(Team.WHITE));
        return whiteChessPieces;
    }

    private Map<ChessBoardPosition, ChessPiece> getBlackChessPieces() {
        Map<ChessBoardPosition, ChessPiece> blackChessPieces = new HashMap<>();
        blackChessPieces.put(ChessBoardPosition.from("a3"), new Pawn(Team.BLACK));
        blackChessPieces.put(ChessBoardPosition.from("c3"), new Pawn(Team.BLACK));
        return blackChessPieces;
    }

    private static Stream<Arguments> diagonalParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, ChessBoardPosition.of('b', 7),
                        ChessBoardPosition.of('a', 6), "흑팀의 폰이 오른쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.BLACK, ChessBoardPosition.of('b', 7),
                        ChessBoardPosition.of('c', 6), "흑팀의 폰은 왼쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.WHITE, ChessBoardPosition.of('b', 2),
                        ChessBoardPosition.of('a', 3), "백팀의 폰은 왼쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.WHITE, ChessBoardPosition.of('b', 2),
                        ChessBoardPosition.of('c', 3), "백팀의 폰은 오른쪽 대각선으로 한 칸 이동한다")
        );
    }
}
