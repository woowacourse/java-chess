package chessgame.domain.piece;

import static chessgame.domain.point.PointFixture.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Team;
import chessgame.domain.point.Points;

public class QueenTest {

    @Test
    @DisplayName("Queen은 대각선으로 움직일 수 있다.")
    void Should_Move_When_Diagonal() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(new Points(List.of(A1, H8)), false, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Queen은 수평으로 움직일 수 있다.")
    void Should_Move_When_Horizontal() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(new Points(List.of(A1, H1)), false, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Queen은 수직으로 움직일 수 있다.")
    void Should_Move_When_Vertical() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(new Points(List.of(H1, H8)), false, false);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Queen의 이동정책에 맞지 않으면, Queen은 움직일 수 없다.")
    void Should_NotMove_When_Unmovable() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(new Points(List.of(A1, F8)), false, false);
        Assertions.assertThat(result).isFalse();
    }
}
