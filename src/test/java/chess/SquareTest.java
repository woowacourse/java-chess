package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    @DisplayName("칸을 생성한다.")
    void create() {
        char rank = 'a';
        int file = 1;

        Assertions.assertThat(Square.of(rank, file)).isInstanceOf(Square.class);
    }
}
