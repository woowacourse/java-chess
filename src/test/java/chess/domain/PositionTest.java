package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("칸을 생성한다.")
    void create() {
        char rank = 'a';
        int file = 1;

        Assertions.assertThat(Position.of(rank, file)).isInstanceOf(Position.class);
    }
}
