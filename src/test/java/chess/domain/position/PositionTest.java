package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    Position position = new Position("a1");

    @ParameterizedTest
    @CsvSource(value = {"b1:true", "a2:false"}, delimiter = ':')
    @DisplayName("포지션 간 세로줄을 비교한다.")
    void isSameFile(String target, boolean expected) {
        //given
        Position targetPosition = new Position(target);

        // when
        boolean actual = position.isSameFile(targetPosition);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:false"}, delimiter = ':')
    @DisplayName("위치의 이름을 비교한다.")
    void testIsSameFile(String target, boolean expected) {
        // when
        boolean actual = position.isSameFile(target);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a2:true", "b1:false"}, delimiter = ':')
    @DisplayName("포지션 간 가로줄을 비교한다.")
    void isSameRank(String target, boolean expected) {
        //given
        Position targetPosition = new Position(target);

        // when
        boolean actual = position.isSameRank(targetPosition);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
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