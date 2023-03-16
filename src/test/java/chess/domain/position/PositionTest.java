package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("포인트간의 랭크의 차이를 계산한다.")
    void calculateRankGap() {
        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.C, Rank.FOUR);

        int rankGap = from.calculateRankGap(to);

        assertThat(rankGap).isEqualTo(-3);
    }

    @Test
    @DisplayName("포인트간의 파일의 차이를 계산한다.")
    void calculateFileGap() {
        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.C, Rank.FOUR);

        int fileGap = from.calculateFileGap(to);

        assertThat(fileGap).isEqualTo(-2);
    }
}
