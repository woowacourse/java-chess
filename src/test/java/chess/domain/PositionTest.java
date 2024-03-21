package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("포지션 생성 테스트")
    void constructTest() {
        Position position = new Position(Row.RANK3, Column.H);

        Assertions.assertThat(position).isNotNull();
    }
}
