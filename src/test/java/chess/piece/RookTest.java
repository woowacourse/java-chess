package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @DisplayName("룩이 이동가능한 경로를 구한다.")
    @Test
    void calculate_rook_can_move_direction() {
        Position departure = new Position(Column.D, Row.TWO);
        Rook rook = new Rook();
        Position arrival = new Position(Column.D, Row.EIGHT);
        List<Position> canAblePositions = rook.calculateCanMovePosition(departure, arrival);

        List<Position> expectedPosition = List.of(
            new Position(Column.D, Row.THREE),
            new Position(Column.D, Row.FOUR),
            new Position(Column.D, Row.FIVE),
            new Position(Column.D, Row.SIX),
            new Position(Column.D, Row.SEVEN),
            new Position(Column.D, Row.EIGHT));
        assertThat(canAblePositions).containsExactlyInAnyOrderElementsOf(expectedPosition);
    }


    @DisplayName("룩이 갈 수 없는 위치라면, 예외를 던져야 한다.")
    @Test
    void if_bishop_cannot_go_position_then_throw_exception() {
        Position departure = new Position(Column.A, Row.ONE);
        Rook rook = new Rook();
        Position arrival = new Position(Column.B, Row.TWO);
        assertThatThrownBy(() -> rook.calculateCanMovePosition(departure, arrival));
    }
}
