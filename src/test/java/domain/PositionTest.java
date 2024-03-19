package domain;

import static domain.Position.A1;
import static domain.Position.A4;
import static domain.Position.A7;
import static domain.Position.B2;
import static domain.Position.B4;
import static domain.Position.B6;
import static domain.Position.C3;
import static domain.Position.C4;
import static domain.Position.C5;
import static domain.Position.D1;
import static domain.Position.D2;
import static domain.Position.D3;
import static domain.Position.D4;
import static domain.Position.D5;
import static domain.Position.D6;
import static domain.Position.D7;
import static domain.Position.D8;
import static domain.Position.E3;
import static domain.Position.E4;
import static domain.Position.E5;
import static domain.Position.F2;
import static domain.Position.F4;
import static domain.Position.F6;
import static domain.Position.G1;
import static domain.Position.G4;
import static domain.Position.G7;
import static domain.Position.H4;
import static domain.Position.H8;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("같은 행 다른 열의 두 Position 사이의 모든 Position 반환하는지 검증")
    void sameRowRoute() {
        Assertions.assertAll(
                () -> assertThat(D4.route(H4)).containsExactlyInAnyOrder(E4, F4, G4),
                () -> assertThat(D4.route(A4)).containsExactlyInAnyOrder(B4, C4)
        );
    }

    @Test
    @DisplayName("다른 행 같은 열의 두 Position 사이의 모든 Position 반환하는지 검증")
    void sameColumnRoute() {
        Assertions.assertAll(
                () -> assertThat(D4.route(D8)).containsExactlyInAnyOrder(D5, D6, D7),
                () -> assertThat(D4.route(D1)).containsExactlyInAnyOrder(D2, D3)
        );
    }

    @Test
    @DisplayName("같은 대각선 상의 두 Position 사이의 모든 Position 반환하는지 검증")
    void sameDiagonalRoute() {
        Assertions.assertAll(
                () -> assertThat(D4.route(H8)).containsExactlyInAnyOrder(E5, F6, G7),
                () -> assertThat(D4.route(A7)).containsExactlyInAnyOrder(C5, B6),
                () -> assertThat(D4.route(A1)).containsExactlyInAnyOrder(C3, B2),
                () -> assertThat(D4.route(G1)).containsExactlyInAnyOrder(E3, F2)
        );
    }
}
