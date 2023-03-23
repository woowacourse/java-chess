package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.Direction.*;
import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @DisplayName("8가지 방향을 검증할 수 있다")
    @Test
    void of() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(B6, D6, F6, F4, F2, D2, B2, B4);
        final List<Direction> expected = List.of(NW, N, NE, E, SE, S, SW, W);

        //when

        //then
        assertThat(destinations).map(destination -> Direction.of(source, destination))
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("8가지 방향이 아니면 NOTHING을 리턴한다")
    @Test
    void ofNothing() {
        //given
        final Position source = D4;
        final Position destination = F5;

        //when

        //then
        assertThat(Direction.of(source, destination)).isEqualTo(Direction.NOTHING);
    }

    @DisplayName("대각선 관계면 true를 반환한다")
    @Test
    void isDiagonal() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(B6, F6, B2, F2);

        //when

        //then
        assertThat(destinations).allMatch(destination -> Direction.isDiagonal(source, destination));
    }

    @DisplayName("대각선 관계가 아니면 false를 반환한다")
    @Test
    void isNotDiagonal() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(B7, F5, C2, H2);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> Direction.isDiagonal(source, destination));
    }

    @DisplayName("직선 관계면 true를 반환한다")
    @Test
    void isStraight() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(D8, D1, A4, F4);

        //when

        //then
        assertThat(destinations).allMatch(destination -> Direction.isStraight(source, destination));
    }

    @DisplayName("직선 관계가 아니면 false를 반환한다")
    @Test
    void isNotStraight() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(H8, B1, A2, F7);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> Direction.isStraight(source, destination));
    }
}
