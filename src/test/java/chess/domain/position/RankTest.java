package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("랭크의 순서에 따라 이동할 수 있다")
    void moveOrderByStepTest() {
        Rank fromRank = Rank.FIVE;
        int step = -3;

        Rank toRank = fromRank.move(step);

        assertThat(toRank).isEqualTo(Rank.TWO);
        assertThat(toRank.subtractOrder(fromRank)).isEqualTo(step);
    }
}
