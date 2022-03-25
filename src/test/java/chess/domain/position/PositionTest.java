package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @DisplayName("생성 확인")
    @Test
    void create() {
        // then
        assertThat(new Position(Column.A, Row.ONE)).isNotNull();
    }

    @DisplayName("좌표 이동")
    @Test
    void move() {
        // given
        Position from = new Position(Column.A, Row.ONE);
        Position to = new Position(Column.A, Row.TWO);

        // when
        Direction direction = from.findDirection(to, true);

        // then
        assertThat(direction).isEqualTo(Direction.NORTH);
    }
}
