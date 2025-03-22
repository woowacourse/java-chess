package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("나이트가 이동가능한 경로를 구한다.")
    @Test
    void calculate_knight_can_move_direction() {
        Position departure = new Position(Column.A, Row.ONE);
        Knight knight = new Knight();
        Position arrival = new Position(Column.B, Row.THREE);
        List<Position> canAblePositions = knight.calculateCanMovePosition(departure, arrival);
        List<Position> expectedPosition = List.of(
            new Position(Column.A, Row.TWO),
            new Position(Column.B, Row.THREE));
        assertThat(canAblePositions).containsExactlyInAnyOrderElementsOf(expectedPosition);
    }

    @DisplayName("나이트가 갈 수 없는 위치라면, 예외를 발생시켜야 한다")
    @Test
    void when_knight_cannot_go_then_throw_exception() {
        Position departure = new Position(Column.A, Row.ONE);
        Knight knight = new Knight();
        Position arrival = new Position(Column.B, Row.ONE);
        assertThatThrownBy(() -> knight.calculateCanMovePosition(departure, arrival));
    }
}
