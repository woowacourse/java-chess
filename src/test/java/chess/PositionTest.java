package chess;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    void constructTest() {
        Position position = new Position(Row.h, Column.RANK3);

        Assertions.assertThat(position).isNotNull();
    }
}
