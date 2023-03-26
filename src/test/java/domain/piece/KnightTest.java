package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("나이트는 ")
class KnightTest {
    @DisplayName("대각선, 행, 열을 제외한 가장 가까운 칸으로 이동할 수 있다.")
    @Test
    void knightMovable() {
        //given
        final Knight knight = new Knight(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(E6, C6, B5, F5, B3, F3, C2, E2);

        //when

        //then
        assertThat(destinations).allMatch(destination -> knight.isMovable(source, destination));
    }

    @DisplayName("대각선, 행, 열을 제외한 가장 가까운 칸이 아니면 이동할 수 없다.")
    @Test
    void knightUnMovable() {
        //given
        final Knight knight = new Knight(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(A8, H5, G6);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> knight.isMovable(source, destination));
    }

    @DisplayName("기본 점수는 2.5점이다.")
    @Test
    void score() {
        //given
        final Knight knight = new Knight(Team.BLACK);

        //when

        //then
        assertThat(knight.getScore()).isEqualTo(2.5);
    }
}
