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

class KingTest {
    @DisplayName("장애물이 없는 경우, 8개 방향으로 한 칸씩 이동할 수 있다.")
    @Test
    void notExistHurdles() {
        // given
        Map<Position, ChessPiece> positions = Map.of();
        King king = new King(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.FIVE, Column.C),
                new Position(Row.FIVE, Column.D),
                new Position(Row.FIVE, Column.E),
                new Position(Row.FOUR, Column.C),
                new Position(Row.FOUR, Column.E),
                new Position(Row.THREE, Column.C),
                new Position(Row.THREE, Column.D),
                new Position(Row.THREE, Column.E)
        );

        // when
        List<Position> destinations = king.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("장애물이 있는 경우, 8개 방향 중에서 장애물이 없는 곳으로 한 칸 이동할 수 있다.")
    @Test
    void existHurdles() {
        // given
        Map<Position, ChessPiece> positions = Map.of(
                new Position(Row.FIVE, Column.D), new Bishop(Color.BLACK),
                new Position(Row.THREE, Column.C), new King(Color.WHITE)
        );
        King king = new King(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.FIVE, Column.C),
                new Position(Row.FIVE, Column.E),
                new Position(Row.FOUR, Column.C),
                new Position(Row.FOUR, Column.E),
                new Position(Row.THREE, Column.D),
                new Position(Row.THREE, Column.E)
        );

        // when
        List<Position> destinations = king.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
