package chess.domain.movement.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightDirectionTest {

    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_KnightDirection_When_CanReachWithReachablePosition_Then_True(int file, int rank) {
        //given
        KnightDirection direction = new KnightDirection();
        Position source = new Position(4, 4);
        Position target = new Position(file, rank);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isTrue();
    }

    @Test
    @DisplayName("도착 위치에 장애물이 있을 경우 거짓을 반환한다.")
    void Given_KnightDirection_When_CanReachWithReachablePositionAndObstacle_Then_False() {
        //given
        KnightDirection direction = new KnightDirection();
        Position source = new Position(4, 4);
        Position target = new Position(2, 5);
        //when, then
        assertThat(direction.canReach(source, target, List.of(new Position(2, 5)))).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 도착 위치까지 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_KnightDirection_When_canReachWithUnreachablePosition_Then_False() {
        //given
        KnightDirection direction = new KnightDirection();
        Position source = new Position(2, 7);
        Position target = new Position(8, 8);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isFalse();
    }
}
