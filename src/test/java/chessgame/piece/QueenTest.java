package chessgame.piece;

import static chessgame.point.PointFixture.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Team;
import chessgame.domain.piece.Queen;

public class QueenTest {

    @Test
    @DisplayName("Queen은 대각선으로 움직일 수 있다.")
    void Should_Move_When_Diagonal() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(A1, H8);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Queen은 수평으로 움직일 수 있다.")
    void Should_Move_When_Horizontal() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(A1, H1);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Queen은 수직으로 움직일 수 있다.")
    void Should_Move_When_Vertical() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(H1, H8);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Queen이 좌우상하대각선외에 움직인다면, Queen은 움직일 수 없다.")
    void Should_NotMove_When_Unmovable() {
        Queen queen = Queen.from(Team.BLACK);
        boolean result = queen.isMovable(A1, F8);
        Assertions.assertThat(result).isFalse();
    }
}
