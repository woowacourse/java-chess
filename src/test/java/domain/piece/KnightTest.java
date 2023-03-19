package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("나이트는 대각선, 행, 열을 제외한 가장 가까운 칸으로 이동할 수 있다.")
    @Test
    void knightMovable() {
        //given
        final Knight knight = new Knight(Team.BLACK);
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "E6", "C6", "B5", "F5", "B3", "F3", "C2", "E2");

        //when

        //then
        assertThat(destinations).allMatch(destination -> knight.isMovable(source, destination));
    }

    @DisplayName("나이트는 대각선, 행, 열을 제외한 가장 가까운 칸이 아니면 이동할 수 없다.")
    @Test
    void knightUnMovable() {
        //given
        final Knight knight = new Knight(Team.BLACK);
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "A8", "H5", "G6");

        //when

        //then
        assertThat(destinations).noneMatch(destination -> knight.isMovable(source, destination));
    }
}
