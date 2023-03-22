package chessgame.piece;

import chessgame.domain.Team;
import chessgame.domain.piece.King;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

public class KingTest {

    @Test
    @DisplayName("King은 수평으로 1칸 움직일 수 있다")
    void Should_Move_When_HorizontalOneDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A1, A2, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("King은 수직으로 1칸 움직일 수 있다")
    void Should_Move_When_VerticalOneDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A1, B1, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("King은 대각선으로 1칸 움직일 수 있다")
    void Should_Move_When_DiagonalOneDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A2, B1, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("King은 수평으로 1칸 초과로는 움직일 수 없다")
    void Should_Move_When_HorizontalTwoDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A1, A3, false);
        Assertions.assertThat(result).isFalse();
    }
}
