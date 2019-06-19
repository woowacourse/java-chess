package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {
    @Test
    public void 동치성_테스트() {
        Row row = Row.from("1");
        Column column = Column.from("a");
        Position position1 = Position.of(row, column);
        Position position2 = Position.of(row, column);

        assertThat(position1).isEqualTo(position2);
    }

    @Test
    public void 캐싱_테스트() {
        Row row = Row.from("1");
        Column column = Column.from("a");
        Position position1 = Position.of(row, column);
        Position position2 = Position.of(row, column);

        assertTrue(position1 == position2);
    }

    @Test
    public void 캐싱_VALUE_테스트() {
        Row row = Row.from("1");
        Column column = Column.from("a");
        Position position = Position.of(row, column);

        assertTrue(Position.of(row, column) == position);
    }

    @Test
    public void 수평_맞는지_테스트() {
        Position position1 = Position.of("5", "a");
        Position position2 = Position.of("5", "b");

        assertThat(position1.isLevel(position2)).isTrue();
    }

    @Test
    public void 수평_아닌지_테스트() {
        Position position1 = Position.of("3", "a");
        Position position2 = Position.of("5", "a");

        assertThat(position1.isLevel(position2)).isFalse();
    }

    @Test
    public void 수직_맞는지_테스트() {
        Position position1 = Position.of("5", "a");
        Position position2 = Position.of("3", "a");

        assertThat(position1.isPerpendicular(position2)).isTrue();
    }

    @Test
    public void 수직_아닌지_테스트() {
        Position position1 = Position.of("5", "a");
        Position position2 = Position.of("5", "b");

        assertThat(position1.isPerpendicular(position2)).isFalse();
    }

    @Test
    public void 대각선_맞는지_테스트() {
        Position position1 = Position.of("1", "a");
        Position position2 = Position.of("8", "h");

        assertThat(position1.isDiagonal(position2)).isTrue();
    }

    @Test
    public void 대각선_아닌지_테스트() {
        Position position1 = Position.of("6", "e");
        Position position2 = Position.of("4", "d");

        assertThat(position1.isDiagonal(position2)).isFalse();
    }
}
