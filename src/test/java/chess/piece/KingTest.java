package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @DisplayName("왕이 이동가능한 경로를 구한다.")
    @Test
    void calculate_king_can_move_direction() {
        Position position = new Position(Column.D, Row.FIVE);
        King king = new King();
        List<Position> canAblePositions = king.calculateCanMovePosition(position);

        List<Position> expectedPosition = List.of(
            new Position(Column.D, Row.SIX),
            new Position(Column.D, Row.FOUR),
            new Position(Column.C, Row.FIVE),
            new Position(Column.E, Row.FIVE),
            new Position(Column.C, Row.SIX),
            new Position(Column.C, Row.FOUR),
            new Position(Column.E, Row.SIX),
            new Position(Column.E, Row.FOUR));

        assertThat(canAblePositions).containsExactlyInAnyOrderElementsOf(expectedPosition);
    }

}
