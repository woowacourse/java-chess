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
        assertThat(Position.of(Column.A, Row.ONE)).isNotNull();
    }

    @DisplayName("이동 방향 찾기 - 한 칸만 이동한 경우")
    @Test
    void find_direction1() {
        // given
        Position from = Position.of(Column.A, Row.ONE);
        Position to = Position.of(Column.A, Row.TWO);

        // when
        Direction direction = from.findDirection(to);

        // then
        assertThat(direction).isEqualTo(Direction.NORTH);
    }

    @DisplayName("이동 방향 찾기 - 여러 칸 이동한 경우")
    @Test
    void find_direction2() {
        // given
        Position from = Position.of(Column.A, Row.ONE);
        Position to = Position.of(Column.A, Row.SIX);

        // when
        Direction direction = from.findDirectionByCompactValue(to);

        // then
        assertThat(direction).isEqualTo(Direction.NORTH);

    }

    @DisplayName("같은 row")
    @Test
    void same_row() {
        // given
        Position position1 = Position.of(Column.A, Row.ONE);

        // then
        assertThat(position1.isSameRow(Row.ONE)).isTrue();
    }

    @DisplayName("같은 column")
    @Test
    void same_column() {
        // given
        Position position1 = Position.of(Column.A, Row.ONE);
        Position position2 = Position.of(Column.A, Row.TWO);

        // then
        assertThat(position1.isSameColumn(position2)).isTrue();
    }

    @DisplayName("이동 가능")
    @Test
    void movable() {
        // given
        Position position = Position.of(Column.A, Row.ONE);
        Direction direction = Direction.NORTH;

        // then
        assertThat(position.movable(direction)).isTrue();
    }

    @DisplayName("이동 불가")
    @Test
    void movable_failed() {
        // given
        Position position = Position.of(Column.A, Row.ONE);
        Direction direction = Direction.SOUTH;

        // then
        assertThat(position.movable(direction)).isFalse();
    }

    @DisplayName("이동 가능")
    @Test
    void move() {
        // given
        Position position = Position.of(Column.A, Row.ONE);
        Position expect = Position.of(Column.A, Row.TWO);
        Direction direction = Direction.NORTH;

        // then
        assertThat(position.move(direction)).isEqualTo(expect);
    }
}
