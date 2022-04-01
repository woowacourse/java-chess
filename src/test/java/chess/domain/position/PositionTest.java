package chess.domain.position;

import chess.view.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    Position position = new Position("a1");

    @ParameterizedTest
    @CsvSource(value = {"1", "b"})
    void makePosition(String text){
        String input = text;
                // then
        assertThatThrownBy(() -> new Position(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치는 가로줄과 세로줄의 정보를 전부 포함해야합니다.");
    }

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
