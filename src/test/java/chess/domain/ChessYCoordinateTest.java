package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessYCoordinateTest {
    @Test
    void movePositive() {
        assertThat(ChessYCoordinate.RANK_2.move(2).get()).isEqualTo(ChessYCoordinate.RANK_4);
    }

    @Test
    void moveNegative() {
        assertThat(ChessYCoordinate.RANK_5.move(-3).get()).isEqualTo(ChessYCoordinate.RANK_2);
    }
}
