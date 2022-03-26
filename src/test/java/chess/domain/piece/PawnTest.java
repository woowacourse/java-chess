package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;
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
        Pawn pawn = new Pawn(team, initialPosition);
        List<ChessPiece> empty = new ArrayList<>();
        boolean result = pawn.isMovable(nextPosition, new ChessMen(empty), new ChessMen(empty));
        assertTrue(result);
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('a', 2),
                        new ChessBoardPosition('a', 3), "흑팀의 폰은 초기상태에서 한칸 전진한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('a', 7),
                        new ChessBoardPosition('a', 6), "백팀의 폰은 초기상태에서 한칸 전진한다"),
                Arguments.of(Team.BLACK, new ChessBoardPosition('a', 2),
                        new ChessBoardPosition('a', 4), "흑팀의 폰은 초기상태에서 두칸 전진한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('a', 7),
                        new ChessBoardPosition('a', 5), "백팀의 폰은 초기상태에서 두칸 전진한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('a', 6),
                        new ChessBoardPosition('a', 5), "백팀의 폰은 초기상태 이후 한칸 전진한다")
        );
    }

    @Test
    @DisplayName("앞으로 3칸 전진할 수 없다")
    void moveTest3() {
        ChessBoardPosition initialPosition = new ChessBoardPosition('a', 2);
        Pawn pawn = new Pawn(Team.BLACK, initialPosition);
        ChessBoardPosition nextPosition = new ChessBoardPosition('a', 5);
        List<ChessPiece> empty = new ArrayList<>();
        boolean result = pawn.isMovable(nextPosition, new ChessMen(empty), new ChessMen(empty));
        assertFalse(result);
    }


    @Test
    @DisplayName("초기상태 이후로는 두 칸 이상 전진할 수 없다.")
    void notMoveTest() {
        ChessBoardPosition initialPosition = new ChessBoardPosition('a', 3);
        Pawn pawn = new Pawn(Team.BLACK, initialPosition);
        ChessBoardPosition nextPosition = new ChessBoardPosition('a', 5);
        List<ChessPiece> empty = new ArrayList<>();
        boolean result = pawn.isMovable(nextPosition, new ChessMen(empty), new ChessMen(empty));
        assertFalse(result);
    }

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("diagonalParameters")
    @DisplayName("폰은 적이 대각선 방향에 존재하는 경우 이동 가능하다.")
    void diagonalMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Pawn pawn = new Pawn(team, initialPosition);
        ChessMen whiteChessMen = getWhiteChessMen();
        ChessMen blackChessMen = getBlackChessMen();
        pawn.isMovable(nextPosition, whiteChessMen, blackChessMen);
        assertTrue(pawn.isMovable(nextPosition, whiteChessMen, blackChessMen));
    }

    private ChessMen getWhiteChessMen() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(new Pawn(Team.WHITE, ChessBoardPosition.of("a3")));
        chessPieces.add(new Pawn(Team.WHITE, ChessBoardPosition.of("c3")));
        return new ChessMen(chessPieces);
    }

    private ChessMen getBlackChessMen() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.add(new Pawn(Team.BLACK, ChessBoardPosition.of("a6")));
        chessPieces.add(new Pawn(Team.BLACK, ChessBoardPosition.of("c6")));
        return new ChessMen(chessPieces);
    }

    private static Stream<Arguments> diagonalParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('b', 2),
                        new ChessBoardPosition('a', 3), "흑팀의 폰이 오른쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.BLACK, new ChessBoardPosition('b', 2),
                        new ChessBoardPosition('c', 3), "흑팀의 폰은 왼쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('b', 7),
                        new ChessBoardPosition('a', 6), "백팀의 폰은 왼쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('b', 7),
                        new ChessBoardPosition('c', 6), "백팀의 폰은 오른쪽 대각선으로 한 칸 이동한다")
        );
    }
}
