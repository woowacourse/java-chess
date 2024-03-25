package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;

import chess.fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @DisplayName("모든 위치를 생성한다.")
    @Test
    void allPositions() {
        List<Square> squares = Square.allPositions();

        assertThat(squares).containsOnlyOnce(
                PositionFixture.A8, PositionFixture.B8, PositionFixture.C8, PositionFixture.D8, PositionFixture.E8, PositionFixture.F8, PositionFixture.G8, PositionFixture.H8,
                PositionFixture.A7, PositionFixture.B7, PositionFixture.C7, PositionFixture.D7, PositionFixture.E7, PositionFixture.F7, PositionFixture.G7, PositionFixture.H7,
                PositionFixture.A6, PositionFixture.B6, PositionFixture.C6, PositionFixture.D6, PositionFixture.E6, PositionFixture.F6, PositionFixture.G6, PositionFixture.H6,
                PositionFixture.A5, PositionFixture.B5, PositionFixture.C5, PositionFixture.D5, PositionFixture.E5, PositionFixture.F5, PositionFixture.G5, PositionFixture.H5,
                PositionFixture.A4, PositionFixture.B4, PositionFixture.C4, PositionFixture.D4, PositionFixture.E4, PositionFixture.F4, PositionFixture.G4, PositionFixture.H4,
                PositionFixture.A3, PositionFixture.B3, PositionFixture.C3, PositionFixture.D3, PositionFixture.E3, PositionFixture.F3, PositionFixture.G3, PositionFixture.H3,
                PositionFixture.A2, PositionFixture.B2, PositionFixture.C2, PositionFixture.D2, PositionFixture.E2, PositionFixture.F2, PositionFixture.G2, PositionFixture.H2,
                PositionFixture.A1, PositionFixture.B1, PositionFixture.C1, PositionFixture.D1, PositionFixture.E1, PositionFixture.F1, PositionFixture.G1, PositionFixture.H1
        );
    }
}
