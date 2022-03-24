import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Direction;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @DisplayName("생성 확인")
    @Test
    void create() {
        // then
        assertThat(new Position(Row.A, Column.ONE)).isNotNull();
    }

    @DisplayName("좌표 이동")
    @Test
    void move() {
        // given
        Position from = new Position(Row.A, Column.ONE);
        Position to = new Position(Row.A, Column.TWO);

        // when
        Direction direction = from.findDirection(to, true);

        // then
        assertThat(direction).isEqualTo(Direction.NORTH);
    }
}
