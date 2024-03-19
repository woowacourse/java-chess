package domain.chessboard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {
    @DisplayName("체스판의 가로 줄을 생성한다.")
    @Test
    void generateRank() {
        Rank rank = new Rank(new RankGenerator(), 0);
        Assertions.assertThat(rank.size()).isEqualTo(8);
    }
}
