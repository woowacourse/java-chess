package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("두 Position의 File 차이 구하기")
    void calculateFileDifference() {
        Position position1 = new Position(new File(1), new Rank(2));
        Position position2 = new Position(new File(2), new Rank(2));
        Assertions.assertThat(position1.calculateFileDifference(position2)).isEqualTo(-1);
    }

    @Test
    @DisplayName("두 Position의 Rank 차이 구하기")
    void calculateRankDifference() {
        Position position1 = new Position(new File(2), new Rank(1));
        Position position2 = new Position(new File(2), new Rank(2));
        Assertions.assertThat(position1.calculateRankDifference(position2)).isEqualTo(-1);
    }
}
