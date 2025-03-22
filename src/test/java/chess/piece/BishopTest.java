package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @DisplayName("장애물이 존재하지 않는 경우, 보드를 벗어나지 않는 영역 내에서 모든 대각선 방향으로 움직일 수 있다.")
    @Test
    void notExistHurdle() {
        // given
        Map<Position, ChessPiece> positions = Map.of();
        Bishop bishop = new Bishop(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.FIVE, Column.C),
                new Position(Row.SIX, Column.B),
                new Position(Row.SEVEN, Column.A),
                new Position(Row.THREE, Column.E),
                new Position(Row.TWO, Column.F),
                new Position(Row.ONE, Column.G),
                new Position(Row.FIVE, Column.E),
                new Position(Row.SIX, Column.F),
                new Position(Row.SEVEN, Column.G),
                new Position(Row.EIGHT, Column.H),
                new Position(Row.THREE, Column.C),
                new Position(Row.TWO, Column.B),
                new Position(Row.ONE, Column.A)
        );

        // when
        List<Position> destinations = bishop.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("장애물이 존재하는 경우, 보드를 벗어나지 않는 영역 내에서 장애물을 마주하기 전까지의 모든 대각선 방향으로 움직일 수 있다.")
    @Test
    void existHurdle() {
        // given
        Map<Position, ChessPiece> positions = Map.of(
                new Position(Row.SIX, Column.F), new Bishop(Color.WHITE),
                new Position(Row.ONE, Column.G), new Bishop(Color.BLACK)
        );
        Bishop bishop = new Bishop(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.FIVE, Column.C),
                new Position(Row.SIX, Column.B),
                new Position(Row.SEVEN, Column.A),
                new Position(Row.THREE, Column.E),
                new Position(Row.TWO, Column.F),
                new Position(Row.FIVE, Column.E),
                new Position(Row.THREE, Column.C),
                new Position(Row.TWO, Column.B),
                new Position(Row.ONE, Column.A)
        );

        // when
        List<Position> destinations = bishop.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
