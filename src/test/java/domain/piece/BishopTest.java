package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @DisplayName("비숍은 대각선 네 방향으로 이동할 수 있다.")
    @Test
    void bishopMovable() {
        //given
        final Bishop bishop = new Bishop(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(H8, A7, A1, G1);

        //when

        //then
        assertThat(destinations).allMatch(destination -> bishop.isMovable(source, destination));
    }

    @DisplayName("비숍은 대각선이 아니면 이동할 수 없다.")
    @Test
    void bishopUnMovable() {
        //given
        final Bishop bishop = new Bishop(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(F8, A6, A2, H3);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> bishop.isMovable(source, destination));
    }
}
