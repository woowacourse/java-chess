package board;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @ParameterizedTest
    @DisplayName("목표 위치값의 x좌표를 가리키는 방향값을 정상적으로 계산해야 함")
    @CsvSource(value = {"a1,a3,0", "b2,e5,1", "f3,d8,-1"})
    void getXPointDirectionValueToTest(String source, String target, int expected) {
        int result = new Position(source).getXPointDirectionValueTo(new Position(target));
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("목표 위치값의 y좌표를 가리키는 방향값을 정상적으로 계산해야 함")
    @CsvSource(value = {"h1,h1,0", "d3,d7,1", "f6,f2,-1"})
    void getYPointDirectionValueToTest(String source, String target, int expected) {
        int result = new Position(source).getYPointDirectionValueTo(new Position(target));
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("현재 위치값에 방향값만큼 더하면 위치값이 정상적으로 변경되야 함")
    @CsvSource(value = {"b2,NORTH,b3", "b2,EAST,c2", "b2,SOUTH,b1", "b2,WEST,a2",
            "b2,NORTHEAST,c3", "b2,SOUTHEAST,c1", "b2,SOUTHWEST,a1", "b2,NORTHWEST,a3",
            "d4,NNE,e6", "d4,NNW,c6", "d4,SSE,e2", "d4,SSW,c2",
            "d4,EEN,f5", "d4,EES,f3", "d4,WWN,b5", "d4,WWS,b3"})
    void positionPlusDirectionThenChangePosition(String source, String direction, String target) {
        Position position = new Position(source);
        position.plus(Direction.valueOf(direction));
        Assertions.assertThat(position).isEqualTo(new Position(target));
    }
}
