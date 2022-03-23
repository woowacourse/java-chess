package chess.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @Test
    @DisplayName("랭크는 8가지로 이루어져 있다.")
    void countRanks() {
        assertThat(Rank.values().length).isEqualTo(8);
    }
}
