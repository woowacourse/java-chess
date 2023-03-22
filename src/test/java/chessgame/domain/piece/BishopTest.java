package chessgame.domain.piece;

import static chessgame.domain.point.PointFixture.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Team;
import chessgame.domain.point.Points;

class BishopTest {

    @Test
    @DisplayName("Bishop 대각선으로 움직일 수 있다.")
    void Should_Move_When_RightDiagonal() {
        Bishop bishop = Bishop.from(Team.BLACK);
        boolean result = bishop.isMovable(new Points(List.of(A1, H8)), false, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Bishop 반대 대각선으로 움직일 수 있다.")
    void Should_Move_When_LeftDiagonal() {
        Bishop bishop = Bishop.from(Team.BLACK);
        boolean result = bishop.isMovable(new Points(List.of(H1, A8)), false, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Bishop의 이동정책에 맞지 않으면, Bishop은 움직일 수 없다.")
    void Should_NotMove_When_Unmovable() {
        Bishop bishop = Bishop.from(Team.BLACK);
        boolean result = bishop.isMovable(new Points(List.of(A1, H1)), false, false);
        Assertions.assertThat(result).isFalse();
    }
}
