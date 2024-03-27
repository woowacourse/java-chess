package chess.domain.movement.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DownLeftDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithReachablePosition_Then_True() {
        //given
        DownLeftDirection direction = new DownLeftDirection(8);
        Position source = new Position(8, 8);
        Position target = new Position(1, 1);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "8,1", "1,8"})
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithUnreachablePosition_Then_False(int file, int rank) {
        //given
        DownLeftDirection direction = new DownLeftDirection(8);
        Position source = new Position(file, rank);
        Position target = new Position(2, 2);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithReachablePositionAndObstacle_Then_False() {
        //given
        DownLeftDirection direction = new DownLeftDirection(8);
        Position source = new Position(8, 8);
        Position target = new Position(1, 1);
        //when, then
        assertThat(direction.canReach(source, target, List.of(new Position(7, 7)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithUnreachableMoveCount_Then_False() {
        //given
        DownLeftDirection direction = new DownLeftDirection(1);
        Position source = new Position(8, 8);
        Position target = new Position(1, 1);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isFalse();
    }
}
