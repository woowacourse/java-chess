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

class BishopTest {
    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("비숍은 대각선 방향으로 칸 수의 제한 없이 움직인다. ")
    void moveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Bishop bishop = new Bishop(team, initialPosition);
        bishop.move(nextPosition);
        assertThat(bishop.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('g', 1), "흑팀의 비숍은 1사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('c', 1), "흑팀의 비숍은 2사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('a', 7), "흑팀의 비숍은 3사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('h', 6), "흑팀의 비숍은 4사분면으로 끝까지 간다.")
        );
    }

    @Test
    @DisplayName("비숍이 타겟위치로 갈 수 없으면 false를 반환")
    void movableTest() {
        ChessPiece bishop = new Bishop(Team.BLACK, new ChessBoardPosition('e', 3));
        List<ChessPiece> blackChessPieces = List.of(bishop,
                new Knight(Team.BLACK, new ChessBoardPosition('d', 4)));
        ChessMen chessMen = new ChessMen(blackChessPieces);
        boolean result = bishop.movable(new ChessBoardPosition('c', 5), chessMen);
        assertFalse(result);
    }

    @Test
    @DisplayName("비숍이 타겟위치로 갈 수 있으면 true를 반환")
    void movableTest2() {
        ChessPiece bishop = new Bishop(Team.WHITE, new ChessBoardPosition('e', 3));
        List<ChessPiece> blackChessPieces = List.of(bishop);
        ChessMen chessMen = new ChessMen(blackChessPieces);
        boolean result = bishop.movable(new ChessBoardPosition('c', 5), chessMen);
        assertTrue(result);
    }
}
