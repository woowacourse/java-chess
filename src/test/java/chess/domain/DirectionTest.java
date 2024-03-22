package chess.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DirectionTest {

    @ParameterizedTest
    @DisplayName("Direction 방향으로 이동한 X 좌표와 Y 좌표를 계산한다.")
    @CsvSource(value = {
            "LEFT,-1,0", "RIGHT,1,0", "UP,0,1", "DOWN,0,-1",
            "LEFT_UP,-1,1", "LEFT_DOWN,-1,-1", "RIGHT_UP,1,1", "RIGHT_DOWN,1,-1",
            "KNIGHT_LEFT_UP,-2,1", "KNIGHT_LEFT_DOWN,-2,-1",
            "KNIGHT_RIGHT_UP,2,1", "KNIGHT_RIGHT_DOWN,2,-1",
            "KNIGHT_UP_LEFT,-1,2", "KNIGHT_UP_RIGHT,1,2",
            "KNIGHT_DOWN_LEFT,-1,-2", "KNIGHT_DOWN_RIGHT,1,-2"
    })
    void calculateNewCoordinates(Direction direction, int directionX, int directionY) {
        int currentX = 1;
        int currentY = 1;

        int nextX = direction.calculateNextFile(currentX);
        int nextY = direction.calculateNextRank(currentY);

        assertThat(nextX).isEqualTo(currentX + directionX);
        assertThat(nextY).isEqualTo(currentY + directionY);
    }

    @ParameterizedTest
    @DisplayName("주어진 좌표 변화에 대한 방향을 찾을 수 있다.")
    @CsvSource(value = {
            "-1,0,LEFT", "1,0,RIGHT", "0,1,UP", "0,-1,DOWN",
            "-1,1,LEFT_UP", "-1,-1,LEFT_DOWN", "1,1,RIGHT_UP", "1,-1,RIGHT_DOWN",
            "-2,1,KNIGHT_LEFT_UP", "-2,-1,KNIGHT_LEFT_DOWN",
            "2,1,KNIGHT_RIGHT_UP", "2,-1,KNIGHT_RIGHT_DOWN",
            "-1,2,KNIGHT_UP_LEFT", "1,2,KNIGHT_UP_RIGHT",
            "-1,-2,KNIGHT_DOWN_LEFT", "1,-2,KNIGHT_DOWN_RIGHT"
    })
    void findDirectionByDelta(int directionX, int directionY, Direction direction) {
        Direction foundDirection = Direction.find(directionX, directionY);

        assertThat(foundDirection).isEqualTo(direction);
    }

    @Test
    @DisplayName("주어진 좌표에 대응하는 방향이 없으면 예외를 발생시킨다.")
    void findDirectionByDeltaFail() {
        assertThatCode(() -> Direction.find(2, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 이동 방향입니다.");
    }
}
