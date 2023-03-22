package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @DisplayName("킹은 여덟 방향으로 한 칸 이동할 수 있다.")
    @Test
    void kingMovable() {
        //given
        final King king = new King(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(C5, D5, E5, C4, E4, C3, D3, E3);

        //when

        //then
        assertThat(destinations).allMatch(destination -> king.isMovable(source, destination));
    }

    @DisplayName("킹은 여덟 방향으로 한 칸이 아니면 이동할 수 없다.")
    @Test
    void kingUnMovable() {
        //given
        final King king = new King(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(B7, D8, G6);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> king.isMovable(source, destination));
    }
}
