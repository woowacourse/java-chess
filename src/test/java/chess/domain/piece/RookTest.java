package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.List;
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
    void initialMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Rook rook = new Rook(team, initialPosition);
        rook.move(nextPosition);
        assertThat(rook.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('e', 8), "흑팀의 룩이 끝까지 전진한다.."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('e', 1), "흑팀의 룩이 끝까지 후진한다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('h', 3), "흑팀의 룩이 끝까지 왼쪽으로 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('a', 3), "흑팀의 룩이 끝까지 오른쪽으로 간다.")
        );
    }

    @Test
    @DisplayName("룩이 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest() {
        ChessPiece rook = new Rook(Team.BLACK, new ChessBoardPosition('e', 3));
        List<ChessPiece> blackChessPieces = List.of(rook,
                new Knight(Team.BLACK, new ChessBoardPosition('e', 5)));
        ChessMen chessMen = new ChessMen(blackChessPieces);
        boolean result = rook.isMovable(new ChessBoardPosition('e', 7), chessMen);
        assertFalse(result);
    }

    @Test
    @DisplayName("룩이 타겟위치로 갈 수 있으면 true를 반환")
    void movableTest2() {
        ChessPiece rook = new Rook(Team.WHITE, new ChessBoardPosition('e', 3));
        List<ChessPiece> blackChessPieces = List.of(rook);
        ChessMen chessMen = new ChessMen(blackChessPieces);
        boolean result = rook.isMovable(new ChessBoardPosition('e', 5), chessMen);
        assertTrue(result);
    }
}
