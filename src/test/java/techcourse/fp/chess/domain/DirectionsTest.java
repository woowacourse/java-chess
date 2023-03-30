package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionsTest {

    @Test
    @DisplayName("출발점과 도착점을 받아 이동가능한 방향이 있다면 true를 반환한다.")
    void has_movable_direction() {
        //given
        final Directions directions = new Directions(List.of(Direction.UP, Direction.DOWN));
        final Position givenSource = Position.of(File.A, Rank.ONE);
        final Position givenTarget = Position.of(File.A, Rank.TWO);
        //when
        final boolean actual = directions.hasMovableDirection(givenSource, givenTarget);
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("출발점과 도착점을 받아 이동가능한 방향이 없다면 false를 반환한다.")
    void has_not_movable_direction() {
        //given
        final Directions directions = new Directions(List.of(Direction.UP_RIGHT, Direction.DOWN));
        final Position givenSource = Position.of(File.A, Rank.ONE);
        final Position givenTarget = Position.of(File.A, Rank.TWO);
        //when
        final boolean actual = directions.hasMovableDirection(givenSource, givenTarget);
        //then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("여러번 움직여서 이동가능한 Direction을 반환한다.")
    void find_reachable_direction() {
        //given
        final Direction expected = Direction.UP;
        final Directions directions = new Directions(List.of(expected, Direction.DOWN));
        final Position givenSource = Position.of(File.A, Rank.ONE);
        final Position givenTarget = Position.of(File.A, Rank.EIGHT);
        //when
        final Direction actual = directions.findReachableDirection(givenSource, givenTarget);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("여러번 움직여서 접근 가능한 Direction이 없다면 예외를 반환한다..")
    void find_reachable_direction_throw() {
        //given
        final Directions directions = new Directions(List.of(Direction.DOWN));
        final Position givenSource = Position.of(File.A, Rank.ONE);
        final Position givenTarget = Position.of(File.A, Rank.EIGHT);
        //when && then
        assertThatThrownBy(() -> directions.findReachableDirection(givenSource, givenTarget))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행마법 상 이동할 수 없는 위치입니다.");
    }
}
