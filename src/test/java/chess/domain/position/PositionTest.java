package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Direction;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @DisplayName("생성 확인")
    @Test
    void create() {
        // then
        assertThat(new Position(Column.A, Row.ONE)).isNotNull();
    }

    @DisplayName("현재 위치와 이동 위치로 이동할 방향을 찾는다.")
    @Test
    void find_direction() {
        // given
        Position from = new Position(Column.A, Row.ONE);
        Position to = new Position(Column.A, Row.TWO);

        // when
        Direction direction = from.findDirection(to, new Rook(Team.WHITE));

        // then
        assertThat(direction).isEqualTo(Direction.NORTH);
    }
}
