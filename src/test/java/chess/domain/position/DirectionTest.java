package chess.domain.position;

import static chess.domain.position.Direction.E;
import static chess.domain.position.Direction.N;
import static chess.domain.position.Direction.NE;
import static chess.domain.position.Direction.NW;
import static chess.domain.position.Direction.S;
import static chess.domain.position.Direction.SE;
import static chess.domain.position.Direction.SW;
import static chess.domain.position.Direction.W;
import static chess.domain.position.Direction.of;
import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("방향을 계산할 수 있다 : a1 -> a2 = N")
    @Test
    void should_ReturnDirectionNorth() {
        assertThat(of(A1, A2)).isEqualTo(N);
    }

    @DisplayName("방향을 계산할 수 있다 : a1 -> b1 = E")
    @Test
    void should_ReturnDirectionEast() {
        assertThat(of(A1, B1)).isEqualTo(E);
    }

    @DisplayName("방향을 계산할 수 있다 : b1 -> a1 = W")
    @Test
    void should_ReturnDirectionWest() {
        assertThat(of(B1, A1)).isEqualTo(W);
    }

    @DisplayName("방향을 계산할 수 있다 : a2 -> a1 = S")
    @Test
    void should_ReturnDirectionSouth() {
        assertThat(of(A2, A1)).isEqualTo(S);
    }

    @DisplayName("방향을 계산할 수 있다 : a1 -> b2 = NE")
    @Test
    void should_ReturnDirectionNorthEast() {
        assertThat(of(A1, B2)).isEqualTo(NE);
    }

    @DisplayName("방향을 계산할 수 있다 : b2 -> a1 = SW")
    @Test
    void should_ReturnDirectionSouthWest() {
        assertThat(of(B2, A1)).isEqualTo(SW);
    }

    @DisplayName("방향을 계산할 수 있다 : b1 -> a2 = NW")
    @Test
    void should_ReturnDirectionNorthWest() {
        assertThat(of(B1, A2)).isEqualTo(NW);
    }

    @DisplayName("방향을 계산할 수 있다 : a2 -> b1 = SE")
    @Test
    void should_ReturnDirectionSoutWest() {
        assertThat(of(A2, B1)).isEqualTo(SE);
    }
}
