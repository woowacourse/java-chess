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

class PawnTest {
    @DisplayName("BLACK - 처음 움직이는 경우 두 칸까지 전진할 수 있다.")
    @Test
    void notExistHurdle() {
        // given
        Map<Position, ChessPiece> positions = Map.of();
        Pawn pawn = new Pawn(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(
                new Position(Row.SEVEN, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
