package chess.domain.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("랭크의 차이를 구한다.")
    void subtractOrderTest() {
        Rank rank = Rank.FIVE;
        Rank other = Rank.ONE;

        int gap = rank.subtractOrder(other);

        Assertions.assertThat(gap).isEqualTo(4);
    }
}
