package chess.domain.position;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "d4", "c5", "h3"})
    @DisplayName("Position의 문자열 값을 조회한다.")
    void getPositionToString(String inputPosition) {
        // given
        Position position = Position.from(inputPosition);

        // when
        String positionToString = position.getPositionToString();

        // then
        assertThat(positionToString).isEqualTo(inputPosition);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "a8", "b4", "d3"})
    @DisplayName("위치 값을 문자열로 입력 받은 후, Position을 조회한다.")
    void from(String input) {
        Position position = Position.from(input);
        assertThat(position.getPositionToString()).isEqualTo(input);
    }

    @ParameterizedTest
    @CsvSource(value = {"c1:c1:true", "f1:f2:false", "b2:b2:true"}, delimiter = ':')
    @DisplayName("Position을 입력 받아, 일치하는지 확인한다.")
    void matchPosition(String input1, String input2, boolean expected) {
        // given
        Position position1 = Position.from(input1);
        Position position2 = Position.from(input2);

        // when
        boolean result = position1.matchPosition(position2);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Direction을 입력 받아, Position에 더한 값을 반환한다.")
    void plusDirection() {
        // given
        Position position = Position.from("a1");
        Direction direction = Direction.EAST;

        // when
        Position result = position.plusDirection(direction);

        // then
        Position expected = Position.from("b1");
        assertThat(result).isEqualTo(expected);
    }
}