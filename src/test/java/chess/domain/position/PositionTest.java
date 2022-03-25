package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    Position position = new Position("a1");

    @ParameterizedTest
    @DisplayName("포지션 간 세로줄을 비교한다.")
    @CsvSource(value = {"b1:true", "a2:false"}, delimiter = ':')
    void isSameFile(String target, boolean expected) {
        //given
        Position targetPosition = new Position(target);

        // when
        boolean actual = position.isSameFile(targetPosition);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("위치의 이름을 비교한다.")
    @CsvSource(value = {"1:true", "2:false"}, delimiter = ':')
    void testIsSameFile(String target, boolean expected) {
        // when
        boolean actual = position.isSameFile(target);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("포지션 간 가로줄을 비교한다.")
    @CsvSource(value = {"a2:true", "b1:false"}, delimiter = ':')
    void isSameRank(String target, boolean expected) {
        //given
        Position targetPosition = new Position(target);

        // when
        boolean actual = position.isSameRank(targetPosition);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("현재 위치를 기반으로 방향을 찾는다.")
    @CsvSource(value = {"d7:N", "f7:NE", "f5:E", "f3:SE", "d3:S", "b3:SW", "b5:W", "b7:NW"}, delimiter = ':')
    void findDirection(String target, Direction expected) {
        // given
        Position position = new Position("d5");
        Position targetPosition = new Position(target);

        // when
        Direction actual = targetPosition.findDirection(position);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("현재 위치의 주변 좌표를 탐색한다.")
    @CsvSource(value = {"N:d6", "NE:e6", "E:e5", "SE:e4", "S:d4", "SW:c4", "W:c5", "NW:c6"}, delimiter = ':')
    void toNextPosition(Direction direction, String expected) {
        // given
        Position position = new Position("d5");

        // when
        Position actual = position.toNextPosition(direction);

        // then
        assertThat(actual.getValue()).isEqualTo(expected);
    }
}
