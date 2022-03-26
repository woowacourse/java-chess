package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.Arrays;
import java.util.List;
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
    void moveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Queen queen = new Queen(team, initialPosition);
        queen.move(nextPosition);
        assertThat(queen.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('g', 1), "퀸은 1사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('c', 1), "퀸은 2사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('a', 7), "퀸은 3사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('h', 6), "퀸은 4사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('e', 8), "퀸이 끝까지 전진한다.."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('e', 1), "퀸이 끝까지 후진한다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('h', 3), "퀸이 끝까지 왼쪽으로 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('a', 3), "퀸이 끝까지 오른쪽으로 간다.")
        );
    }

    @Test
    @DisplayName("퀸이 대각선 방향의 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest() {
        ChessPiece queen = new Queen(Team.BLACK, new ChessBoardPosition('e', 3));
        List<ChessPiece> blackChessPieces = List.of(queen,
                new Knight(Team.BLACK, new ChessBoardPosition('d', 4)));
        ChessMen blackChessMen = new ChessMen(blackChessPieces);
        ChessMen whiteChessMen = new ChessMen(Arrays.asList());
        boolean result = queen.isMovable(new ChessBoardPosition('c', 5), whiteChessMen, blackChessMen);
        assertFalse(result);
    }

    @Test
    @DisplayName("퀸이 상하좌우 방향의 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest2() {
        ChessPiece queen = new Queen(Team.BLACK, new ChessBoardPosition('e', 3));
        List<ChessPiece> blackChessPieces = List.of(queen,
                new Knight(Team.BLACK, new ChessBoardPosition('e', 4)));
        ChessMen blackChessMen = new ChessMen(blackChessPieces);
        ChessMen whiteChessMen = new ChessMen(Arrays.asList());
        boolean result = queen.isMovable(new ChessBoardPosition('e', 5), whiteChessMen, blackChessMen);
        assertFalse(result);
    }

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("퀸이 타겟위치로 갈 수 있으면 true를 반환")
    void movableTest2(Team team, ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece queen = new Queen(team, sourcePosition);
        List<ChessPiece> blackChessPieces = List.of(queen);
        ChessMen blackChessMen = new ChessMen(blackChessPieces);
        ChessMen whiteChessMen = new ChessMen(Arrays.asList());
        boolean result = queen.isMovable(targetPosition, whiteChessMen, blackChessMen);
        assertTrue(result);
    }
}
