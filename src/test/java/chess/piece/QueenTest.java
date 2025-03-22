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

class QueenTest {
    @DisplayName("장애물이 존재하지 않는 경우, 8개 방향으로 끝까지 이동할 수 있다.")
    @Test
    void notExistHurdles() {
        // given
        Map<Position, ChessPiece> positions = Map.of();
        Queen queen = new Queen(Color.BLACK);
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
                new Position(Row.ONE, Column.A),
                new Position(Row.EIGHT, Column.D),
                new Position(Row.SEVEN, Column.D),
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D),
                new Position(Row.THREE, Column.D),
                new Position(Row.TWO, Column.D),
                new Position(Row.ONE, Column.D),
                new Position(Row.FOUR, Column.A),
                new Position(Row.FOUR, Column.B),
                new Position(Row.FOUR, Column.C),
                new Position(Row.FOUR, Column.E),
                new Position(Row.FOUR, Column.F),
                new Position(Row.FOUR, Column.G),
                new Position(Row.FOUR, Column.H)
        );

        // when
        List<Position> destinations = queen.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
