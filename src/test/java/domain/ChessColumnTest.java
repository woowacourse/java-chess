package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessColumnTest {

    @Test
    @DisplayName("ChessColumn의 차이를 계산한다.")
    void calculateDifference() {
        //given
        ChessColumn e = ChessColumn.E;
        ChessColumn d = ChessColumn.D;
        //when

        //then
        Assertions.assertThat(e.minus(d)).isEqualTo(1);
        Assertions.assertThat(d.minus(e)).isEqualTo(-1);
    }
}
