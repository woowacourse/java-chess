package chessgame;

import static chessgame.PointFixture.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Team;
import chessgame.domain.piece.Knight;
import chessgame.domain.point.Points;

class KnightTest {
    @Test
    @DisplayName("knight는 수직으로 한칸 이동 후 진행방향으로 대각선을 한칸 이동할 수 있다.")
    void Should_Move_When_VerticalOneDistance() {
        Knight knight = Knight.from(Team.BLACK);
        boolean result = knight.isMovable(new Points(List.of(A1, B3)), false, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("knight는 수평으로 한칸 이동 후 진행방향으로 대각선을 한칸 이동할 수 있다.")
    void Should_Move_When_HorizontalOneDistance() {
        Knight knight = Knight.from(Team.BLACK);
        boolean result = knight.isMovable(new Points(List.of(H8, F7)), false, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("knight는 수평으로 한칸 이동 후 역진행방향으로 대각선을 한칸 이동할 수 없다.")
    void Should_NotMove_When_HorizontalOneDistanceReverse() {
        Knight knight = Knight.from(Team.BLACK);
        boolean result = knight.isMovable(new Points(List.of(F8, F7)), false, false);
        Assertions.assertThat(result).isFalse();
    }
}
