package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("Rank에 1~8이 있는지 확인한다.")
    void contain() {
        assertThat(Rank.values()).containsOnly(Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR,
            Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT);
    }
}
