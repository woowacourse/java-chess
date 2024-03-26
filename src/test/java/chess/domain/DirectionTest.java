package chess.domain;


import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class DirectionTest {

    @ParameterizedTest
    @DisplayName("Direction 방향으로 이동한 X 좌표와 Y 좌표를 계산한다.")
    @CsvSource(value = {
            "LEFT,B,THREE,", "RIGHT,D,THREE", "UP,C,FOUR", "DOWN,C,TWO",
            "LEFT_UP,B,FOUR", "LEFT_DOWN,B,TWO", "RIGHT_UP,D,FOUR", "RIGHT_DOWN,D,TWO",
            "KNIGHT_LEFT_UP,A,FOUR", "KNIGHT_LEFT_DOWN,A,TWO",
            "KNIGHT_RIGHT_UP,E,FOUR", "KNIGHT_RIGHT_DOWN,E,TWO",
            "KNIGHT_UP_LEFT,B,FIVE", "KNIGHT_UP_RIGHT,D,FIVE",
            "KNIGHT_DOWN_LEFT,B,ONE", "KNIGHT_DOWN_RIGHT,D,ONE"
    })
    void calculateNewCoordinates(Direction direction, File expectedFile, Rank extectdRank) {
        File currentFile = File.C;
        Rank currentRank = Rank.THREE;

        File nextFile = direction.moveFile(currentFile);
        Rank nextRank = direction.moveRank(currentRank);

        assertThat(nextFile).isEqualTo(expectedFile);
        assertThat(nextRank).isEqualTo(extectdRank);
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
                .hasMessage("존재하지 않는 이동 방향입니다.");
    }
}
