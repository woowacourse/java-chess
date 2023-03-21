package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.Bishop;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class BishopTest {
    @Test
    @DisplayName("Bishop 대각선으로 움직일 수 있다.")
    void Should_Move_When_RightDiagonal() {
        Bishop bishop = Bishop.from(Team.BLACK);
        boolean result = bishop.isMovable(A1, H8);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Bishop 반대 대각선으로 움직일 수 있다.")
    void Should_Move_When_LeftDiagonal() {
        Bishop bishop = Bishop.from(Team.BLACK);
        boolean result = bishop.isMovable(H1, A8);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Bishop이 대각선으로 이동하지 않는다면, Bishop은 움직일 수 없다.")
    void Should_NotMove_When_Unmovable() {
        Bishop bishop = Bishop.from(Team.BLACK);
        boolean result = bishop.isMovable(A1, H1);
        Assertions.assertThat(result).isFalse();
    }
}
