package domain.chessboard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankLineTest {
    @DisplayName("체스판의 가로 줄을 생성한다.")
    @Test
    void generateRank() {
        RankLine rankLine = new RankLine(new RankGenerator(), 0);
        Assertions.assertThat(rankLine.size()).isEqualTo(8);
    }
}
