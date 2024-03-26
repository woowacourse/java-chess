package chess.domain.position;

import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.A5;
import static chess.fixture.PositionFixture.A6;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.A8;
import static chess.fixture.PositionFixture.B1;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.B4;
import static chess.fixture.PositionFixture.B5;
import static chess.fixture.PositionFixture.B6;
import static chess.fixture.PositionFixture.B7;
import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.C1;
import static chess.fixture.PositionFixture.C2;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.C4;
import static chess.fixture.PositionFixture.C5;
import static chess.fixture.PositionFixture.C6;
import static chess.fixture.PositionFixture.C7;
import static chess.fixture.PositionFixture.C8;
import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D3;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.D6;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.D8;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.E6;
import static chess.fixture.PositionFixture.E7;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.F5;
import static chess.fixture.PositionFixture.F6;
import static chess.fixture.PositionFixture.F7;
import static chess.fixture.PositionFixture.F8;
import static chess.fixture.PositionFixture.G1;
import static chess.fixture.PositionFixture.G2;
import static chess.fixture.PositionFixture.G3;
import static chess.fixture.PositionFixture.G4;
import static chess.fixture.PositionFixture.G5;
import static chess.fixture.PositionFixture.G6;
import static chess.fixture.PositionFixture.G7;
import static chess.fixture.PositionFixture.G8;
import static chess.fixture.PositionFixture.H1;
import static chess.fixture.PositionFixture.H2;
import static chess.fixture.PositionFixture.H3;
import static chess.fixture.PositionFixture.H4;
import static chess.fixture.PositionFixture.H5;
import static chess.fixture.PositionFixture.H6;
import static chess.fixture.PositionFixture.H7;
import static chess.fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("모든 위치를 생성한다.")
    @Test
    void allPositions() {
        List<Position> positions = Position.allPositions();

        assertThat(positions).containsOnlyOnce(
                A8, B8, C8, D8, E8, F8, G8, H8,
                A7, B7, C7, D7, E7, F7, G7, H7,
                A6, B6, C6, D6, E6, F6, G6, H6,
                A5, B5, C5, D5, E5, F5, G5, H5,
                A4, B4, C4, D4, E4, F4, G4, H4,
                A3, B3, C3, D3, E3, F3, G3, H3,
                A2, B2, C2, D2, E2, F2, G2, H2,
                A1, B1, C1, D1, E1, F1, G1, H1
        );
    }
}
