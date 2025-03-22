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

class KnightTest {
    @DisplayName("나이트는 목적지를 제외한 경로 상에 장애물 존재 여부와 상관 없이 이동할 수 있다.")
    @Test
    void noMatterHurdle() {
        Map<Position, ChessPiece> positions = Map.of(
                new Position(Row.FIVE, Column.D), new King(Color.WHITE),
                new Position(Row.FOUR, Column.C), new Bishop(Color.BLACK)
        );
        Knight knight = new Knight(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.SIX, Column.C),
                new Position(Row.FIVE, Column.B),
                new Position(Row.SIX, Column.E),
                new Position(Row.FIVE, Column.F),
                new Position(Row.THREE, Column.B),
                new Position(Row.TWO, Column.C),
                new Position(Row.TWO, Column.E),
                new Position(Row.THREE, Column.F)
        );

        // when
        List<Position> destinations = knight.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("목적지에 같은 팀의 기물이 있는 경우, 목적지 후보에서 제외된다.")
    @Test
    void existSameTeamAtDestination() {
        Map<Position, ChessPiece> positions = Map.of(
                new Position(Row.SIX, Column.C), new King(Color.BLACK),
                new Position(Row.THREE, Column.F), new Bishop(Color.BLACK)
        );
        Knight knight = new Knight(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.FIVE, Column.B),
                new Position(Row.SIX, Column.E),
                new Position(Row.FIVE, Column.F),
                new Position(Row.THREE, Column.B),
                new Position(Row.TWO, Column.C),
                new Position(Row.TWO, Column.E)
        );

        // when
        List<Position> destinations = knight.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
