package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("비숍이 이동가능한 경로를 구한다.")
    @Test
    void calculate_king_can_move_direction() {
        Position departure = new Position(Column.A, Row.ONE);
        Bishop bishop = new Bishop();
        Position arrival = new Position(Column.C, Row.THREE);
        List<Position> canAblePositions = bishop.calculateCanMovePosition(departure, arrival);

        List<Position> expectedPosition = List.of(
            new Position(Column.B, Row.TWO),
            new Position(Column.C, Row.THREE));
        assertThat(canAblePositions).containsExactlyInAnyOrderElementsOf(expectedPosition);
    }

    @DisplayName("비숍이 갈 수 없는 위치라면, 예외를 던져야 한다.")
    @Test
    void if_bishop_cannot_go_position_then_throw_exception() {
        Position departure = new Position(Column.A, Row.ONE);
        Bishop bishop = new Bishop();
        Position arrival = new Position(Column.A, Row.TWO);
        assertThatThrownBy(() -> bishop.calculateCanMovePosition(departure, arrival));
    }
}
