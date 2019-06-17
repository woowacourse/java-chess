package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @Test
    void 동치성_테스트() {
        Row row = Row.from("1");
        Column column = Column.from("a");
        Position position1 = Position.of(row, column);
        Position position2 = Position.of(row, column);

        assertThat(position1).isEqualTo(position2);
    }
}
