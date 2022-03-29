package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.model.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("Rank을 이동한다.")
    @Test
    void add() {
        assertThat(ONE.add(-1)).isEqualTo(TWO);
    }
}
