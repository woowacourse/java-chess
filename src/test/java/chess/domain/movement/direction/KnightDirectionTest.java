package chess.domain.movement.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightDirectionTest {
    //TODO: 장애물에 대한 명확한 정의 필요 e.g ) 우리 말
    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void canReach(int row, int column) {
        KnightDirection direction = new KnightDirection();
        Position from = new Position(4, 4);
        Position to = new Position(column, row);
        assertThat(direction.canReach(from, to, List.of())).isTrue();
    }

    @Test
    @DisplayName("도착 위치에 장애물이 있을 경우 거짓을 반환한다.")
    void canNotReachWhenObstacleExist() {
        KnightDirection direction = new KnightDirection();
        Position from = new Position(4, 4);
        Position to = new Position(2, 5);
        assertThat(direction.canReach(from, to, List.of(new Position(2, 5)))).isFalse();
    }

    @Test
    @DisplayName("현재 위치에서 도착 위치까지 이동이 불가능한 경우 거짓을 반환한다.")
    void canNotReachWhenNowIsBoundary() {
        KnightDirection direction = new KnightDirection();
        Position from = new Position(2, 7);
        Position to = new Position(8, 8);
        assertThat(direction.canReach(from, to, List.of())).isFalse();
    }
}
