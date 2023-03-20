package chessgame.piece;

import static chessgame.point.PointFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Team;
import chessgame.domain.piece.King;

public class KingTest {
    @Test
    @DisplayName("블랙팀이면, 이름이 대문자로 바뀌어서 저장되는지 확인한다.")
    void Should_UpperCaseName_When_BlackTeam() {
        String name = "k";
        King king = King.from(Team.BLACK);
        assertThat(king.toString()).isEqualTo(name.toUpperCase());
    }

    @Test
    @DisplayName("화이트팀이면, 이름이 소문자로 바뀌어서 저장되는지 확인한다.")
    void Should_LowerCaseName_When_WhiteTeam() {
        String name = "K";
        King king = King.from(Team.WHITE);
        assertThat(king.toString()).isEqualTo(name.toLowerCase());
    }

    @Test
    @DisplayName("King은 수평으로 1칸 움직일 수 있다")
    void Should_Move_When_HorizontalOneDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A1, A2);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("King은 수직으로 1칸 움직일 수 있다")
    void Should_Move_When_VerticalOneDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A1, B1);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("King은 대각선으로 1칸 움직일 수 있다")
    void Should_Move_When_DiagonalOneDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A2, B1);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("King은 수평으로 1칸 초과로는 움직일 수 없다")
    void Should_Move_When_HorizontalTwoDistance() {
        King king = King.from(Team.BLACK);
        boolean result = king.isMovable(A1, A3);
        Assertions.assertThat(result).isFalse();
    }
}
