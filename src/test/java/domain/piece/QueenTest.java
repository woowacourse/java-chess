package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("퀸은 ")
class QueenTest {

    @DisplayName("여덟 방향으로 이동할 수 있다.")
    @Test
    void queenMovable() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(A7, D5, A1, C4, D1, C3, G7, E3);

        //when

        //then
        assertThat(destinations).allMatch(destination -> queen.isMovable(source, destination));
    }

    @DisplayName("여덟 방향이 아니면 이동할 수 없다.")
    @Test
    void queenUnMovable() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        Position source = D4;
        final List<Position> destinations = List.of(A8, B5, G6);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> queen.isMovable(source, destination));
    }

    @DisplayName("기본 점수는 9점이다.")
    @Test
    void score() {
        //given
        final Queen queen = new Queen(Team.BLACK);

        //when

        //then
        assertThat(queen.getScore()).isEqualTo(9);
    }
}
