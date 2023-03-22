package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.Knight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class KnightTest {
    @Test
    @DisplayName("knight는 수직으로 한칸 이동 후 진행방향으로 대각선을 한칸 이동할 수 있다.")
    void Should_Move_When_VerticalOneDistance() {
        Knight knight = Knight.from(Team.BLACK);
        boolean result = knight.isMovable(A1, B3, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("knight는 수평으로 한칸 이동 후 진행방향으로 대각선을 한칸 이동할 수 있다.")
    void Should_Move_When_HorizontalOneDistance() {
        Knight knight = Knight.from(Team.BLACK);
        boolean result = knight.isMovable(H8, F7, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("knight는 수평으로 한칸 이동 후 역진행방향으로 대각선을 한칸 이동할 수 없다.")
    void Should_NotMove_When_HorizontalOneDistanceReverse() {
        Knight knight = Knight.from(Team.BLACK);
        boolean result = knight.isMovable(F8, F7, false);
        Assertions.assertThat(result).isFalse();
    }
}
