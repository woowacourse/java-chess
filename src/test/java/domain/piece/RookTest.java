package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("룩은 직선으로 이동할 수 있다.")
    @Test
    void rookMovable() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "D3", "D5", "D1", "D8", "A4", "C4", "E4", "H4");

        //when

        //then
        assertThat(destinations).allMatch(destination -> rook.isMovable(source, destination));
    }

    @DisplayName("룩은 직선이 아니면 이동할 수 없다.")
    @Test
    void rookUnMovable() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        Position source = Positions.from("D4");
        final List<Position> destinations = Positions.of(
                "A8", "B5", "G6");

        //when

        //then
        assertThat(destinations).noneMatch(destination -> rook.isMovable(source, destination));
    }
}
