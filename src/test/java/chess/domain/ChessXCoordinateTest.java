package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessXCoordinateTest {

    @Test
    void movePositive() {
        assertThat(ChessXCoordinate.A.move(3)).isEqualTo(ChessXCoordinate.D);
    }

    @Test
    void moveNegative() {
        assertThat(ChessXCoordinate.D.move(-2)).isEqualTo(ChessXCoordinate.B);
    }

    @Test
    void getAscendingCoordinates() {
        List<ChessXCoordinate> actual = ChessXCoordinate.getAscendingCoordinates(ChessXCoordinate.D);

        assertThat(actual).containsExactly(ChessXCoordinate.E, ChessXCoordinate.F, ChessXCoordinate.G, ChessXCoordinate.H);
    }
}
