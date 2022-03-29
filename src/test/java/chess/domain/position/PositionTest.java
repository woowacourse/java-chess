package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest
    @DisplayName("유효하지 않은 값으로 포지션을 찾으면 예외를 던진다.")
    @ValueSource(strings = {"i1", "a9", "i9", "a", "1", "."})
    void validate(final String value) {
        // then
        assertThatThrownBy(() -> Position.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("포지션 간 세로줄을 비교한다.")
    @CsvSource(value = {"b1:true", "a2:false"}, delimiter = ':')
    void isSameFile(final String target, final boolean expected) {
        //given
        final Position position = Position.from("a1");
        final Position targetPosition = Position.from(target);

        // when
        final boolean actual = position.isSameRank(targetPosition);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("위치의 이름을 비교한다.")
    @CsvSource(value = {"1:true", "2:false"}, delimiter = ':')
    void testIsSameFile(final String target, final boolean expected) {
        // given
        final Position position = Position.from("a1");

        // when
        final boolean actual = position.isSameRank(target);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("포지션 간 가로줄을 비교한다.")
    @CsvSource(value = {"a2:true", "b1:false"}, delimiter = ':')
    void isSameRank(final String target, final boolean expected) {
        //given
        final Position position = Position.from("a1");
        final Position targetPosition = Position.from(target);

        // when
        final boolean actual = position.isSameFile(targetPosition);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("현재 위치를 기반으로 방향을 찾는다.")
    @CsvSource(value = {"d7:N", "f7:NE", "f5:E", "f3:SE", "d3:S", "b3:SW", "b5:W", "b7:NW"}, delimiter = ':')
    void findDirection(final String target, final Direction expected) {
        // given
        final Position position = Position.from("d5");
        final Position targetPosition = Position.from(target);

        // when
        final Direction actual = targetPosition.findDirection(position);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("현재 위치의 주변 좌표를 탐색한다.")
    @CsvSource(value = {"N:d6", "NE:e6", "E:e5", "SE:e4", "S:d4", "SW:c4", "W:c5", "NW:c6"}, delimiter = ':')
    void toNextPosition(final Direction direction, final String expectedPosition) {
        // given
        final Position position = Position.from("d5");
        final Position expected = Position.from(expectedPosition);

        // when
        final Position actual = position.toNextPosition(direction);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
