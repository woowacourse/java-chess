package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("Board를 초기화한다.")
    void create() {
        assertThat(Board.create()).isInstanceOf(Board.class);
    }
}
