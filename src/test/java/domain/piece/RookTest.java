package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("룩은 ")
class RookTest {
    @DisplayName("직선으로 이동할 수 있다.")
    @Test
    void rookMovable() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(D3, D5, D1, D8, A4, C4, E4, H4);

        //when

        //then
        assertThat(destinations).allMatch(destination -> rook.isMovable(source, destination));
    }

    @DisplayName("직선이 아니면 이동할 수 없다.")
    @Test
    void rookUnMovable() {
        //given
        final Rook rook = new Rook(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(A8, B5, G6);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> rook.isMovable(source, destination));
    }

    @DisplayName("기본 점수는 5점이다.")
    @Test
    void score() {
        //given
        final Rook rook = new Rook(Team.BLACK);

        //when

        //then
        assertThat(rook.getScore()).isEqualTo(5);
    }
}
