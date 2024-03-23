package domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("현재 Rank와 특정 Rank의 차를 계산")
    void subtract() {
        Rank rank1 = Rank.valueOf(1);
        Rank rank2 = Rank.valueOf(2);

        assertThat(rank1.subtract(rank2)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Rank가 2인지 여부 반환")
    void isRankTwo() {
        Rank rank = Rank.valueOf(2);
        assertThat(rank.isRankTwo()).isTrue();
    }
}
