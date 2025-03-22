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

class RookTest {
    @DisplayName("룩 - 장애물이 존재하지 않을 경우 이동 경로 끝까지 이동할 수 있다.")
    @Test
    void notExistHurdles() {
        // given
        Position rookPosition = new Position(Row.FOUR, Column.D);
        Rook rook = new Rook(Color.BLACK);
        Map<Position, ChessPiece> positions = Map.of(
                rookPosition, rook
        );
        List<Position> expected = List.of(
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
        List<Position> destinations = rook.getAvailableDestinations(
                rookPosition, positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
