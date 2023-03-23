package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("대각선이면 true를 반환한다")
    @Test
    void isDiagonal() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(E5, C5, E3, C3);

        //when

        //then
        assertThat(destinations).allMatch(destination -> Direction.isDiagonal(source, destination));
    }

    @DisplayName("대각선이 아니면 false를 반환한다")
    @Test
    void isNotDiagonal() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(A5, C1, F3, H3);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> Direction.isDiagonal(source, destination));
    }

    @DisplayName("직선이면 true를 반환한다")
    @Test
    void isStraight() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(D1, D7, A4, H4);

        //when

        //then
        assertThat(destinations).allMatch(destination -> Direction.isStraight(source, destination));
    }

    @DisplayName("직선이 아니면 false를 반환한다")
    @Test
    void isNotStraight() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(A3, H7, C7, H8);

        //when

        //then
        assertThat(destinations).noneMatch(destination -> Direction.isStraight(source, destination));
    }

    @DisplayName("거리를 맨허튼거리로 계산해 반환한다")
    @Test
    void getDistance() {
        //given
        final Position source = D4;
        final List<Position> destinations = List.of(A1, H4, H6, C2);
        final List<Integer> expected = List.of(6, 4, 6, 3);

        //when

        //then
        assertThat(destinations).map(source::getDistance)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("아래로 한 칸 움직인 위치를 구할 수 있다")
    @Test
    void moveDown() {
        //given
        final Position source = D4;
        final Position expected = D3;

        //when

        //then
        assertThat(source.move(Direction.S)).isEqualTo(expected);
    }

    @DisplayName("위로 한 칸 움직인 위치를 구할 수 있다")
    @Test
    void moveUp() {
        //given
        final Position source = D4;
        final Position expected = D5;

        //when

        //then
        assertThat(source.move(Direction.N)).isEqualTo(expected);
    }

    @DisplayName("직선 사이의 위치들을 구할 수 있다")
    @Test
    void between() {
        //given
        final Position source = D4;
        final Position destination = D8;
        final List<Position> expected = List.of(D5, D6, D7);

        //when

        //then
        assertThat(source.between(destination)).containsExactlyElementsOf(expected);
    }
}
